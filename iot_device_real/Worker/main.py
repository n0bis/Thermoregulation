from machine import Pin
import dht
from utime import sleep_ms, ticks_ms
from BluetoothBroadcaster import ESP32_BLE

# Update timings (Interval to send data is avg_count * sample_interval)
sample_interval = 1  # Interval in which to sample the temperature !Should not be lower than 1!
avg_count = 5      # How many samples to average before sending


# Temperature Sensors
t1 = dht.DHT11(Pin(32, Pin.IN))
t2 = dht.DHT11(Pin(33, Pin.IN))
t3 = dht.DHT11(Pin(26, Pin.IN))
t4 = dht.DHT11(Pin(27, Pin.IN))
sensors = [t1, t2, t3, t4]
temp1 = 0
temp2 = 0
temp3 = 0
temp4 = 0
temps = [temp1, temp2, temp3, temp4]

# Setup Bluetooth
bt_bc = ESP32_BLE("ESP32BLE_reciever")
bt_bc.scanForReciever()


def resetTemps():
    for i in range(0,4):
        temps[i] = 0


count = 0

sleep_ms(5000)
# Main Loop
while True:
    sleep_ms(sample_interval*1000)
    for i in range(0,4):
        sensors[i].measure()
        temps[i] = temps[i] + sensors[i].temperature()
    count += 1
    
    print(count)
    if count == avg_count:
        avg_temp1 = temps[0] / avg_count
        avg_temp2 = temps[1] / avg_count
        avg_temp3 = temps[2] / avg_count
        avg_temp4 = temps[3] / avg_count
        resetTemps()
        
        if bt_bc.isConnected():
            msg = ("%s;%s;%s;%s" %(avg_temp1, avg_temp2, avg_temp3, avg_temp4))
            print("sending message: %s" %(msg))
            try:
                bt_bc.sendMsg(msg)
            except OSError as e:
                bt_bc.disconnect()
                print("Connection lost")
        else:
            try:
                bt_bc.scanForReciever()
            except OSError as e:
                print("scanning for bluetooth device")
            
        count = 0
        