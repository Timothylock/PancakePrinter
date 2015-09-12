import sys
import serial
import time

data = sys.argv[1];
print("Opening serial connection with the Arduino")
ser = serial.Serial('COM4', 9600, timeout=0)
time.sleep(2)
print("Now printing the first layer. Please wait...")
splitData = data.split(",")
print(splitData)
time.sleep(10000);
ser.write(dark)

file = open("finish.f", "w")
file.write("hello world in the new file\n")
file.close()

