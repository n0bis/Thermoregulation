from machine import Pin, RTC
from utime import sleep_ms, ticks_ms
from BluetoothBroadcaster import ESP32_BLE
from math import ceil
import time
import dht
import gc

gc.collect()

# Update timings (Interval to send data is avg_count * sample_interval)
sample_interval = 1  # Interval in which to sample the temperature !Should not be lower than 1!
avg_count = 5      # How many samples to average before sending

# Temperature Sensors
t1 = dht.DHT11(Pin(27, Pin.IN))
temp1 = []

# Setup Bluetooth
bt_bc = ESP32_BLE("ESP32BLE_reciever")
bt_bc.scanForReciever()

# Storage in case of connection errors
temp_store = []

def freeMem():
    f = gc.mem_free()
    a = gc.mem_alloc()
    t = f+a
    return (f/t * 100)

def send_data(msg):
    print("sending message: %s" %(msg))
    try:
        bt_bc.sendMsg(msg)
    except OSError as e:
        bt_bc.disconnect()
        print("Connection lost")

def send_stored_data():
    for d in temp_store:
        msg = create_msg(d)
        send_data(msg)
        sleep_ms(1000)
    temp_store.clear()
        

def create_msg(temps):
    return "%s;%s;" %(temps[0], temps[1])
    

sample_count = 0
sleep_ms(2000)

# Main Loop
while True:
    sleep_ms(sample_interval*1000)
    
    t1.measure()
    temp1.append(t1.temperature())
    sample_count += 1
    
    print(sample_count)
    
    if sample_count == avg_count:
        timestamp = time.time()
        print(timestamp)
        avg_temp = (sum(temp1)/ avg_count)
        temp1.clear()
        
        current_temp = [timestamp, avg_temp]
        
        if bt_bc.isConnected():
            if len(temp_store) > 0:
                send_stored_data()
            msg = create_msg(current_temp)
            send_data(msg)
            
        else:
            print(freeMem())
            if freeMem() < 30:
                temp_store = temp_store[2-1::2] #Delete every 2nd stored temperature set to make room for new data (decreases resolution)
                gc.collect()
                
                
            temp_store.append(current_temp)
                
            try:
                bt_bc.scanForReciever()
            except OSError as e:
                print("scanning for bluetooth device")
            
        sample_count = 0
        