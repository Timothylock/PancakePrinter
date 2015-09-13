import sys
import serial
import time

file = open('data.txt', 'r')
data = file.read()
file.close();

#data = "30|26|1,30|27|1,30|27|1,30|28|1,30|28|1,30|29|1,30|29|1,30|30|1,30|30|1,30|31|1,30|31|1,30|32|1,30|32|1,30|33|1,30|34|1,30|34|1,30|34|1,30|34|1,30|35|1,30|35|1,30|36|1,30|37|1,30|37|1,30|37|1,30|38|1,30|38|1,30|38|1,30|38|1,30|39|1,30|40|1,30|40|1,30|41|0,30|41|0,30|42|0,30|42|0,30|42|0,30|42|0,30|43|0,30|43|0,31|44|0,31|45|0,31|45|0,31|46|0,31|46|0,31|46|0,31|47|0,31|47|0,31|47|0,31|47|0,31|48|0,31|48|0,68|26|1,68|27|1,68|27|1,68|28|1,68|28|1,68|29|1,68|29|1,68|29|1,68|30|1,68|30|1,68|30|1,68|31|1,68|31|1,68|32|1,69|33|1,69|34|1,69|35|1,69|36|1,69|36|1,69|37|1,69|37|1,69|38|1,68|39|1,68|39|1,68|40|1,68|41|1,68|41|1,68|41|1,68|42|1,68|42|0,68|42|0,68|42|0,68|43|0,68|43|0,68|43|0,68|44|0,68|44|0,68|44|0,68|45|0,68|45|0,68|45|0,68|46|0,68|46|0,68|46|0,68|47|0,68|47|0,68|47|0,68|47|0,68|48|0,25|61|1,25|61|1,25|61|1,25|62|1,25|62|1,25|62|1,25|63|1,25|63|1,25|63|1,26|64|1,26|65|1,26|65|1,27|65|1,27|66|1,27|67|1,28|68|1,28|68|1,29|68|1,29|69|1,30|69|1,30|69|1,32|70|1,33|71|1,33|71|1,34|71|1,34|72|1,34|72|1,35|72|1,35|72|1,36|72|1,36|72|1,36|73|1,36|73|1,36|73|1,37|73|1,38|74|1,40|74|1,42|75|1,44|75|1,45|75|1,45|75|1,46|76|1,46|76|1,48|76|1,48|76|1,49|76|1,49|76|1,49|76|1,50|77|1,51|77|1,52|77|1,53|77|1,54|77|1,55|78|1,56|78|1,56|78|1,57|78|1,58|78|1,59|78|1,60|78|1,60|78|1,60|78|1,61|78|1,61|78|1,62|78|1,64|78|1,65|78|1,65|77|1,66|77|1,67|77|1,69|77|1,69|76|1,69|76|1,70|76|1,70|76|1,71|75|1,71|75|0,71|75|0,72|75|0,72|74|0,72|74|0,73|73|0,74|72|0,74|71|0,75|71|0,75|71|0,76|69|0,78|67|0,78|66|0,78|66|0,78|66|0,78|65|0,79|64|0,79|64|0,79|64|0,79|63|0,SWITCH,99|10|1,99|13|1,99|15|1,99|16|1,99|18|1,99|18|1,98|20|1,98|20|1,98|21|1,98|22|1,98|23|1,98|25|1,98|27|1,98|29|1,98|30|1,98|31|1,98|31|1,98|31|1,98|32|1,98|33|1,98|34|1,98|36|1,98|36|1,98|37|1,98|38|1,98|39|1,98|40|1,99|41|1,99|42|1,99|43|1,99|45|1,99|47|1,99|49|1,99|54|1,99|55|1,99|56|1,99|56|1,99|57|1,99|61|1,99|63|1,99|64|1,99|65|1,99|66|1,99|66|1,99|66|1,99|67|1,99|68|1,99|69|1,99|70|1,99|74|1,99|75|1,99|76|1,99|76|1,99|77|1,98|78|1,97|81|1,97|82|1,97|82|1,97|82|1,96|82|1,96|83|1,95|83|1,95|83|1,95|83|1,94|83|1,94|83|1,93|83|1,93|83|1,92|83|1,91|82|1,90|82|1,86|80|1,85|79|1,84|78|1,83|77|1,82|76|1,82|75|1,81|75|1,81|74|1,81|74|1,80|73|1,80|70|1,80|65|1,79|63|1,79|61|1,79|59|1,79|58|1,79|57|1,79|55|1,79|55|1,79|54|1,79|53|1,78|52|1,78|52|1,78|51|1,78|51|1,77|48|1,77|47|1,77|44|1,77|43|1,77|41|1,77|40|1,77|39|1,77|38|1,77|36|1,77|35|1,77|33|1,77|32|1,77|30|1,76|30|1,75|28|1,75|27|1,74|25|1,74|23|1,74|22|1,73|20|1,73|19|1,73|18|1,73|15|1,73|13|1,73|12|1,73|11|1,72|11|1,72|11|1,72|11|1,70|10|1,64|8|1,63|7|1,63|7|1,63|7|1,62|7|1,62|7|1,60|7|1,60|7|1,59|8|1,58|8|1,58|8|1,56|9|1,56|9|1,56|10|1,55|10|1,55|10|1,55|11|1,54|12|1,53|12|1,53|13|1,52|14|1,52|15|1,52|16|1,52|17|1,51|18|1,51|19|1,50|21|1,49|23|1,48|25|1,48|25|1,48|25|1,47|26|1,47|28|1,44|33|1,43|34|1,42|36|1,42|37|1,42|38|1,41|39|1,41|40|1,41|42|1,40|43|1,40|44|1,39|46|1,39|46|1,39|49|1,39|50|1,39|51|1,39|52|1,39|53|1,38|53|1,38|53|1,38|53|1,38|54|1,38|56|1,37|60|1,37|62|1,37|63|1,37|64|1,36|66|1,36|66|1,36|68|1,35|69|1,35|71|1,34|73|1,34|74|1,34|75|1,34|75|1,34|75|1,33|75|1,32|76|1,30|76|1,28|76|1,26|76|1,24|76|1,23|75|1,22|74|1,19|72|1,17|70|1,14|67|1,13|65|1,13|62|1,12|50|0,12|46|0,12|45|0,11|44|0,11|43|0,11|42|0,12|39|0,12|38|0,12|34|0,12|32|0,13|31|0,13|30|0,13|30|0,13|30|0,13|29|0,13|28|0,15|26|0,17|23|0,17|23|0,19|22|0,30|26|1,30|27|1,30|27|1,30|28|1,30|28|1,30|29|1,30|29|1,30|30|1,30|30|1,30|31|1,30|31|1,30|32|1,30|32|1,30|33|1,30|34|1,30|34|1,30|34|1,30|34|1,30|35|1,30|35|1,30|36|1,30|37|1,30|37|1,30|37|1,30|38|1,30|38|1,30|38|1,30|38|1,30|39|1,30|40|1,30|40|1,30|41|0,30|41|0,30|42|0,30|42|0,30|42|0,30|42|0,30|43|0,30|43|0,31|44|0,31|45|0,31|45|0,31|46|0,31|46|0,31|46|0,31|47|0,31|47|0,31|47|0,31|47|0,31|48|0,31|48|0,68|26|1,68|27|1,68|27|1,68|28|1,68|28|1,68|29|1,68|29|1,68|29|1,68|30|1,68|30|1,68|30|1,68|31|1,68|31|1,68|32|1,69|33|1,69|34|1,69|35|1,69|36|1,69|36|1,69|37|1,69|37|1,69|38|1,68|39|1,68|39|1,68|40|1,68|41|1,68|41|1,68|41|1,68|42|1,68|42|0,68|42|0,68|42|0,68|43|0,68|43|0,68|43|0,68|44|0,68|44|0,68|44|0,68|45|0,68|45|0,68|45|0,68|46|0,68|46|0,68|46|0,68|47|0,68|47|0,68|47|0,68|47|0,68|48|0,25|61|1,25|61|1,25|61|1,25|62|1,25|62|1,25|62|1,25|63|1,25|63|1,25|63|1,26|64|1,26|65|1,26|65|1,27|65|1,27|66|1,27|67|1,28|68|1,28|68|1,29|68|1,29|69|1,30|69|1,30|69|1,32|70|1,33|71|1,33|71|1,34|71|1,34|72|1,34|72|1,35|72|1,35|72|1,36|72|1,36|72|1,36|73|1,36|73|1,36|73|1,37|73|1,38|74|1,40|74|1,42|75|1,44|75|1,45|75|1,45|75|1,46|76|1,46|76|1,48|76|1,48|76|1,49|76|1,49|76|1,49|76|1,50|77|1,51|77|1,52|77|1,53|77|1,54|77|1,55|78|1,56|78|1,56|78|1,57|78|1,58|78|1,59|78|1,60|78|1,60|78|1,60|78|1,61|78|1,61|78|1,62|78|1,64|78|1,65|78|1,65|77|1,66|77|1,67|77|1,69|77|1,69|76|1,69|76|1,70|76|1,70|76|1,71|75|1,71|75|0,71|75|0,72|75|0,72|74|0,72|74|0,73|73|0,74|72|0,74|71|0,75|71|0,75|71|0,76|69|0,78|67|0,78|66|0,78|66|0,78|66|0,78|65|0,79|64|0,79|64|0,79|64|0,79|63|0,SWITCH,99|10|1,99|13|1,99|15|1,99|16|1,99|18|1,99|18|1,98|20|1,98|20|1,98|21|1,98|22|1,98|23|1,98|25|1,98|27|1,98|29|1,98|30|1,98|31|1,98|31|1,98|31|1,98|32|1,98|33|1,98|34|1,98|36|1,98|36|1,98|37|1,98|38|1,98|39|1,98|40|1,99|41|1,99|42|1,99|43|1,99|45|1,99|47|1,99|49|1,99|54|1,99|55|1,99|56|1,99|56|1,99|57|1,99|61|1,99|63|1,99|64|1,99|65|1,99|66|1,99|66|1,99|66|1,99|67|1,99|68|1,99|69|1,99|70|1,99|74|1,99|75|1,99|76|1,99|76|1,99|77|1,98|78|1,97|81|1,97|82|1,97|82|1,97|82|1,96|82|1,96|83|1,95|83|1,95|83|1,95|83|1,94|83|1,94|83|1,93|83|1,93|83|1,92|83|1,91|82|1,90|82|1,86|80|1,85|79|1,84|78|1,83|77|1,82|76|1,82|75|1,81|75|1,81|74|1,81|74|1,80|73|1,80|70|1,80|65|1,79|63|1,79|61|1,79|59|1,79|58|1,79|57|1,79|55|1,79|55|1,79|54|1,79|53|1,78|52|1,78|52|1,78|51|1,78|51|1,77|48|1,77|47|1,77|44|1,77|43|1,77|41|1,77|40|1,77|39|1,77|38|1,77|36|1,77|35|1,77|33|1,77|32|1,77|30|1,76|30|1,75|28|1,75|27|1,74|25|1,74|23|1,74|22|1,73|20|1,73|19|1,73|18|1,73|15|1,73|13|1,73|12|1,73|11|1,72|11|1,72|11|1,72|11|1,70|10|1,64|8|1,63|7|1,63|7|1,63|7|1,62|7|1,62|7|1,60|7|1,60|7|1,59|8|1,58|8|1,58|8|1,56|9|1,56|9|1,56|10|1,55|10|1,55|10|1,55|11|1,54|12|1,53|12|1,53|13|1,52|14|1,52|15|1,52|16|1,52|17|1,51|18|1,51|19|1,50|21|1,49|23|1,48|25|1,48|25|1,48|25|1,47|26|1,47|28|1,44|33|1,43|34|1,42|36|1,42|37|1,42|38|1,41|39|1,41|40|1,41|42|1,40|43|1,40|44|1,39|46|1,39|46|1,39|49|1,39|50|1,39|51|1,39|52|1,39|53|1,38|53|1,38|53|1,38|53|1,38|54|1,38|56|1,37|60|1,37|62|1,37|63|1,37|64|1,36|66|1,36|66|1,36|68|1,35|69|1,35|71|1,34|73|1,34|74|1,34|75|1,34|75|1,34|75|1,33|75|1,32|76|1,30|76|1,28|76|1,26|76|1,24|76|1,23|75|1,22|74|1,19|72|1,17|70|1,14|67|1,13|65|1,13|62|1,12|50|0,12|46|0,12|45|0,11|44|0,11|43|0,11|42|0,12|39|0,12|38|0,12|34|0,12|32|0,13|31|0,13|30|0,13|30|0,13|30|0,13|29|0,13|28|0,15|26|0,17|23|0,17|23|0,19|22|0,"

print("Opening serial connection with the Arduino")
ser = serial.Serial('COM3', 9600, timeout=0)
time.sleep(2)
print("Now printing the first layer. Please wait...")
splitData = data.split(",")
print(splitData)
for item in splitData:
    ser.write(item)
    print(item)
    time.sleep(0.1)
    if item == "SWITCH":
        print("PAUSING FOR 30 SECONDS")
        ser.write("0|0|0");
        time.sleep(30)

ser.write("0|0|0");
file = open("finish.f", "w")
file.write("hello world in the new file\n")
file.close()

