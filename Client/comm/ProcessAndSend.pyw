import sys
import serial
import time

dark = sys.argv[1];
print("Opening serial connection with the Arduino")
ser = serial.Serial('COM4', 9600, timeout=0)
time.sleep(2)
print("Now printing the first layer. Please wait...")
ser.write(dark)

file = open("finish.f", "w")
file.write("hello world in the new file\n")
file.close()

