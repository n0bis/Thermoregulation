from machine import Pin, ADC, SoftI2C
from utime import sleep_ms, ticks_ms
from BluetoothReciever import ESP32_BLE
from simple import MQTTClient
import time
import network
import ssd1306
import json

print("main")


#LED Setup
red = Pin(27, Pin.OUT)
green = Pin(25, Pin.OUT)
blue = Pin(26, Pin.OUT)
blink_freq = 1 # time the led should stay on and off during blinking in seconds
blink_active = False

r_on = False
g_on = False
b_on = False
toggle_on = False

red.on()
green.off()
blue.on()

#OLED Setup
i2c = SoftI2C(scl=Pin(22), sda=Pin(21))

oled_width = 128
oled_height = 64
oled = ssd1306.SSD1306_I2C(oled_width, oled_height, i2c)

#Timefix
time_fix = 946678200

#MQTT config
client_id = ubinascii.hexlify(machine.unique_id())
mqtt_server = '192.168.1.179'
mqtt_port_send = '1883'
mqtt_port_recieve = '1884'

topic_pub_temp = b'TEMPERATURE'
top_sub_action = b'rules/alert'

#message timing
last_message = 0
message_interval = 0.5 #in seconds

#BLE stuff
ble_msg = ""
mqtt_msg = ""

def connect_mqtt(server, port, sub=False):
    global client_id
    
    client = MQTTClient(client_id, server, port, keepalive=9999)
    client.set_callback(recieve_msg)
    client.connect()
    print('Connected to %s:%s MQTT broker' % (server, port))
    
    if sub:
        client.subscribe(top_sub_action,qos=0)
        
    return client

# Updates the screen with the newest Time and Temperature data
def update_screen(timestamp, temp):
    date = time.localtime(timestamp)[3:5]
    date_str = "{:02} : {:02}".format(date[0], date[1])
    temp_str = "{}C".format(temp)
    
    oled.fill(0)
    
    oled.text(str(date_str), 64, 0)
    oled.hline(0, 8, 128, 1)
    oled.text("Temperature:", 0, 20)
    oled.text(temp_str, 0, 30)
    
    oled.show()

# Callback method for incoming mqtt messages
def recieve_msg(topic, msg):
    try: 
        mqtt_msg = msg.decode('UTF-8') 
        print("message: %s" %(mqtt_msg))
        action = mqtt_msg.split(":")
        do_action(action[1].strip())
    except Exception as e:
        print("unexpected message recieved: %s" %(mqtt_msg))
    
    
# Handles the incoming action from the rules engine
def do_action(action):
    global r_on, g_on, b_on
    
    if action == "stop":
        red.on()
        green.off()
        blue.on()
        r_on = False
        g_on = False
        b_on = False 
    elif action == "red":
        r_on = True
        g_on = False
        b_on = False
    elif action == "green":
        r_on = False
        g_on = True
        b_on = False
    elif action == "blue":
        r_on = False
        g_on = False
        b_on = True

# Toggles the LED to make it flash
def toggle_led():
    global r_on, g_on, b_on, toggle_on
    if toggle_on:
        if r_on:
            red.off()
        if g_on:
            green.off()
        if b_on:
            blue.off() 
    else:
        red.on()
        green.on()
        blue.on()
        
    toggle_on = not toggle_on


client_send = None
client_recieve = None
# Connect to MQTT
try:
    client_send = connect_mqtt(mqtt_server, mqtt_port_send)
except OSError as e:
    client_send = None
    print("offline mode")
    
try:
    client_recieve = connect_mqtt(mqtt_server, mqtt_port_recieve, True)
except OSError as e:
    client_send = None
    print("offline mode")
    
# Setup Bluetooth Connection
bt_reciever = ESP32_BLE("ESP32BLE_reciever")


# Timers to create non-blocking sleep
message_timer = time.ticks_ms()
blink_timer = time.ticks_ms()

# Main Loop
while True:
    current_time = time.ticks_ms()
    if r_on or g_on or b_on:
        if time.ticks_diff(current_time, blink_timer) > blink_freq*1000: #Toggles the LED according to blink frequency
            blink_timer = time.ticks_ms()
            toggle_led()
        
    
    if bt_reciever.isConnected:
        if(time.ticks_diff(current_time, message_timer) > message_interval*1000): #Every message interval
            message_timer = time.ticks_ms()
            msg = bt_reciever.getMsg()
            if msg == "Connected":
                print("Bluetooth connection has been established")
                msg = ""
                bt_reciever.resetMessage()
            
            if client_recieve is not None:
                client_recieve.check_msg()
                
            if msg != "":
                
                bt_reciever.resetMessage()
                print("Update: %s" %(msg))
                
                split_msg = msg.split(';')
                timestamp_small = int(split_msg[0])
                timestamp = timestamp_small + time_fix
                temperature = split_msg[1]

                update_screen(timestamp_small, temperature)
                
                json_temp = '{"value":%s, "timestamp": %s}' %(temperature, timestamp)
                
                if client_send is not None:
                    print("sending data to mqtt")
                    client_send.publish(topic_pub_temp, json_temp)
