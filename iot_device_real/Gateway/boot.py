import time
import ubinascii
import micropython
import network
import esp
import machine
from machine import Pin, ADC
import gc
esp.osdebug(None)
gc.collect()

print("Booting ...")
print("----------------------\n\n")

client_id = ubinascii.hexlify(machine.unique_id())

#Wifi config
ssid = 'PatricksWifi'
password = '123456778'

#Setup Wifi connection
station = network.WLAN(network.STA_IF)
station.active(True)
station.disconnect()


def connectWifi():
    try:
        if not station.isconnected():
            station.connect(ssid, password)
    except OSError as e:
        print("...")
        
    if station.isconnected():
        print("Device connected to network: %s" %(ssid))
    else:
        print("Device could not connect to a network")

connectWifi()

count = 0
total = 0
while not station.isconnected():
    if count == 5:
        count = 0
        connectWifi()
    if total == 60:
        break
    
    count += 1
    total += 1
    time.sleep(1)
    
print("Device connected to network: %s" %(ssid))
print("\n\n----------------------")
print("... Done")