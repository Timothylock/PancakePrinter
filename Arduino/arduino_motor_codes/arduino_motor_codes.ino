// Arduino motor and pump code
// Created by: Bryan Wong and Timothy Lock
// Date: 9th September 2015

// --------------------------------------------------------------------------- Important variables
String rawData = "";
int storedX = 0;
int storedY = 0;
int xCoord = 0;
int yCoord = 0;
int power;

// --------------------------------------------------------------------------- Motors
int motorY[] = {3, 2};
int motorX[] = {5, 6};
int pump[] = {10, 12};
int step_delayY = 1;


void setup() {
  Serial.begin(9600);
 
  // ------------------------------------------------------------------------- Setup motors
  int i;
  for(i = 0; i < 2; i++){
    pinMode(motorY[i], OUTPUT);
    pinMode(motorX[i], OUTPUT);
    pinMode(pump[i], OUTPUT);
}
  // ------------------------------------------------------------------------- Reset X conveyor
  moveX(-100);
  moveY(-250);
  delay(1000);
  moveX(10);
  
  

}

void loop() {
  // ------------------------------------------------------------------------- Collecting list data from serial monitor
  while (Serial.available()){
    delay (3);
    char c = Serial.read();
    rawData += c;
  }
  
  // -------------------------------------------------------------------------- Obtain values from the string sent
  if (rawData.length() > 0){
    if (rawData == "SWITCH"){
      xCoord = 0;
      yCoord = 0;
      power = 0;
    }
    else{
    String a = rawData.substring(0,rawData.indexOf("|"));
    String b = rawData.substring(rawData.indexOf("|")+1,rawData.length()-2);
    String c = rawData.substring(rawData.length()-1);
    xCoord = a.toInt();  
    yCoord = b.toInt(); 
    power = c.toInt();
    
    } 

  // -------------------------------------------------------------------------- 250 for the length, 100 for the width
  int differenceX = xCoord - storedX;
  int differenceY = yCoord - storedY;

  // -------------------------------------------------------------------------- Motor movements
  moveX(differenceX);
  moveY(differenceY);

  // -------------------------------------------------------------------------- To get previous values to track location
  storedX = xCoord;
  storedY = yCoord;
  }
  
  // -------------------------------------------------------------------------- For active pump or inactive pump
  pumpState(power);
  
  // -------------------------------------------------------------------------- Clear data collected from serial
  rawData = "";
  delay(100);
}

void moveY(int steps){
  int i;
  int decreasing=0;

  // --------------------------------------------------------------------------- Appropriate motor timings for Y
  if (steps >= 0){
    digitalWrite(motorY[0], LOW); 
    digitalWrite(motorY[1], HIGH); 
   for(i = 0; i <= abs(steps); i++){
      delay(step_delayY * 8 );}
  }else{
    digitalWrite(motorY[0], HIGH); 
    digitalWrite(motorY[1], LOW); 
   for(i = 0; i <= abs(steps); i++){
      delay(step_delayY * 8 );}
  }

  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
}

void moveX(int steps){
  int i;
  int decreasing =0;

  // ----------------------------------------------------------------------------- Appropriate motor timings for X
  if (steps >= 0){
    digitalWrite(motorX[0], LOW); 
    digitalWrite(motorX[1], HIGH);
    for(i = 0; i <= abs(steps); i++){
      delay(step_delayY*2);
  }}else{
    digitalWrite(motorX[0], HIGH); 
    digitalWrite(motorX[1], LOW); 
    for(i = 0; i <= abs(steps); i++){
      delay(step_delayY *2);
  }


  }
  digitalWrite(motorX[0], LOW); 
  digitalWrite(motorX[1], LOW); 
}

void pumpState(int state){

  // ----------------------------------------------------------------------------- Enabling pump motor
  if (state == 1){
    digitalWrite(pump[0], HIGH); 
    digitalWrite(pump[1], LOW); 
  }else{
    digitalWrite(pump[0], LOW); 
    digitalWrite(pump[1], LOW); 
  }
}


