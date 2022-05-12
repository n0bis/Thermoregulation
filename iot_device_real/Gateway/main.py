from machine import Pin, ADC, SoftI2C
from utime import sleep_ms, ticks_ms
from umqttsimple import MQTTClient
from BluetoothReciever import ESP32_BLE
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

on = True
red.on()
green.on()
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
mqtt_server = "ENTER MQTT IP HERE"
topic_pub_temp = b'test'
top_sub_action = b'actions'

#message timing
last_message = 0
message_interval = 0.5 #in seconds

#BLE stuff
ble_msg = ""
mqtt_msg = ""


def connect_mqtt():
    global client_id, mqtt_server
    client = MQTTClient(client_id, mqtt_server)
    client.connect()
    print('Connected to %s MQTT broker' % (mqtt_server))
    client.set_callback(recieve_msg)
    client.subscribe(top_sub_action)
    return client

def update_screen(timestamp, temp):
    date = time.localtime(timestamp)[3:5]
    date_str = "{:02} : {:02}".format(date[0], date[1])
    temp_str = "Temperature: {}".format(temp)
    
    oled.fill(0)
    
    oled.text(str(date_str), 64, 0)
    oled.hline(0, 8, 128, 1)
    oled.text(temp_str, 0, 15)
    
    oled.show()
    
def recieve_msg(topic, msg):
    mqtt_msg = msg.decode('UTF-8')
    print(mqtt_msg)
    x = json.loads(mqtt_msg)
    do_action(x["actions"])

def do_action(action):
    global blink_active
    if action == "blink":
        blink_active = True
    else:
        blink_active = False
            
def toggle_led():
    global on
    if on:
        red.off()
        on = False
    else:
        red.on()
        on = True
    

# Connect to MQTT
try:
    client = connect_mqtt()
except OSError as e:
    client = None
    print("offline mode")
    
# Setup Bluetooth Connection
bt_reciever = ESP32_BLE("ESP32BLE_reciever")


message_timer = time.ticks_ms()
blink_timer = time.ticks_ms()
# Main Loop
while True:
    current_time = time.ticks_ms()
    
    if blink_active:
        if time.ticks_diff(current_time, blink_timer) > blink_freq*1000:
            blink_timer = time.ticks_ms()
            print("toggle")
            toggle_led()
        
    
    if bt_reciever.isConnected:
        if(time.ticks_diff(current_time, message_timer) > message_interval*1000): #Every message interval
            message_timer = time.ticks_ms()
            msg = bt_reciever.getMsg()
            if msg == "Connected":
                print("Bluetooth connection has been established")
                msg = ""
                bt_reciever.resetMessage()
            
            if client is not None:
                client.check_msg()
                
            if msg != "":
                
                bt_reciever.resetMessage()
                print("Update: %s" %(msg))
                
                split_msg = msg.split(';')
                timestamp_small = int(split_msg[0])
                timestamp = timestamp_small + time_fix
                temperature = split_msg[1]

                update_screen(timestamp_small, temperature)
                
                json_temp = '''{"value":%s, timestamp: %s}''' %(temperature, timestamp)
                
                if client is not None:
                    client.publish(topic_pub_temp, json_temp)
        
    
