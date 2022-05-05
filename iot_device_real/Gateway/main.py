from lcdDriver import GpioLcd
from machine import Pin, ADC
from utime import sleep_ms, ticks_ms
from lmtx import lmt86_v2t
from umqttsimple import MQTTClient
from BluetoothReciever import ESP32_BLE
import network
import json

print("main")

#Pins
rs = Pin(32)
e_p = Pin(33)
D0 = Pin(21)
D1 = Pin(19)
D2 = Pin(18)
D3 = Pin(5)
D4 = Pin(17)
D4 = Pin(25)
D5 = Pin(26)
D6 = Pin(27)
D7 = Pin(14)

temp_pin = ADC(Pin(35))
temp_pin.atten(ADC.ATTN_11DB)
temp_offset = 300

#MQTT config
client_id = ubinascii.hexlify(machine.unique_id())
mqtt_server = '' #INSERT MQTT SERVER IP HERE
topic_pub_temp = b'test'

#message timing
last_message = 0
message_interval = 1 #in seconds

#BLE stuff
ble_msg = ""

#LCD setup
lcd = GpioLcd(rs, e_p, d0_pin=D0, d1_pin=D1, d2_pin=D2, d3_pin=D3, d4_pin=D4, d5_pin=D5, d6_pin=D6, d7_pin=D7, num_lines=2, num_columns=16)
degree = bytearray([0x04,0x0A,0x04,0x00,0x00,0x00,0x00,0x00]) #Create a custom "degree" symbol
lcd.custom_char(0, degree) #Save the custom symbol


def connect_mqtt():
    print("hello")
    global client_id, mqtt_server
    client = MQTTClient(client_id, mqtt_server)
    client.connect()
    print('Connected to %s MQTT broker' % (mqtt_server))
    return client

def update_lcd(message):
    lcd.clear()
    lcd.putstr(message)
    
def get_temp_string(temps):
    s = "s1: %sC%s  s2: %sC%s \ns3: %sC%s  s4: %sC%s"
    temp_msg = s % (temps[0], chr(0), temps[1], chr(0), temps[2], chr(0), temps[3], chr(0),)
    return temp_msg
    

# Connect to MQTT
client = connect_mqtt()

# Setup Bluetooth Connection
bt_reciever = ESP32_BLE("ESP32BLE_reciever")

lcd.clear()

# Main Loop
while True:
    if bt_reciever.isConnected:
        sleep_ms(500)
        msg = bt_reciever.getMsg()
        if msg == "Connected":
            print("Bluetooth connection has been established")
            msg = ""
            bt_reciever.resetMessage()
            
        if msg != "":
            bt_reciever.resetMessage()
            print("Update: %s" %(msg))
            
            split_temps = msg.split(';')
            temps = [float(i) for i in split_temps]
            temp_msg = get_temp_string(temps)
            update_lcd(temp_msg)
            
            temperatureSum = sum(temps)
            temperature = temperatureSum/4
            
            json_temp = '''{"value":%s}''' %(temperature)
            
            
            client.publish(topic_pub_temp, json_temp)

    
