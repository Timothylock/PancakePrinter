import sys
import serial
import time

data = sys.argv[1];
#data = "231|261|1,232|261|1,233|261|1,234|261|1,238|266|1,238|267|1,239|269|1,240|270|1,241|270|1,244|273|1,245|274|1,245|275|1,247|275|1,247|276|1,248|276|1,249|276|1,250|278|1,251|278|1,253|278|1,254|279|1,257|281|1,259|281|1,261|281|1,262|282|1,263|282|1,264|283|1,267|283|0,268|284|0,269|284|0,271|284|0,272|284|0,275|285|0,277|285|0,278|286|0,278|287|0,280|287|0,281|288|0,283|288|0,284|288|0,284|289|0,286|290|0,287|290|0,291|290|0,292|291|0,293|291|0,293|292|0,277|315|1,283|315|1,284|315|1,285|315|1,286|315|0,287|314|0,290|314|0,291|314|0,292|313|0,293|313|0,294|313|0,295|313|0,297|312|0,298|312|0,299|312|0,300|312|0,302|311|0,304|310|0,305|310|0,306|310|0,307|310|0,308|310|0,310|310|0,311|310|0,SWITCH,231|261|1,232|261|1,233|261|1,234|261|1,"
print("Opening serial connection with the Arduino")
ser = serial.Serial('COM4', 9600, timeout=0)
time.sleep(2)
print("Now printing the first layer. Please wait...")
splitData = data.split(",")
print(splitData)
for item in splitData:
    ser.write(item)
    print(item)
    time.sleep(0.5)
    if item == "SWITCH":
        print("PAUSING FOR 30 SECONDS")
        time.sleep(30)

file = open("finish.f", "w")
file.write("hello world in the new file\n")
file.close()

