// --------------------------------------------------------------------------- Motors
int motorY[] = {3, 2};
int motorX[] = {5, 6};
int pump[] = {10, 12};
int step_delayY = 1;

String rawData = "";
int storedX = 0;
int storedY = 0;
int xCoord = 0;
int yCoord = 0;


// --------------------------------------------------------------------------- Setup
void setup() {
Serial.begin(9600);

// Setup motors
int i;
for(i = 0; i < 2; i++){
  pinMode(motorY[i], OUTPUT);
  pinMode(motorX[i], OUTPUT);
  pinMode(pump[i], OUTPUT);
}

Serial.begin(9600);

  moveX(100);
  delay(1000);
  moveX(10);
  moveY(400);

}

// --------------------------------------------------------------------------- Loop
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
      moveX(150);
      moveY(600);
    }
    else{
    String a = rawData.substring(0,rawData.indexOf("|"));
    String b = rawData.substring(rawData.indexOf("|")+1,rawData.length()-2);
    String c = rawData.substring(rawData.length()-1);
    int xCoord = a.toInt();  
    int yCoord = b.toInt(); 

    }
  }


  moveY(250);
  moveX(80);
  delay(100);
  moveY(-250);
  moveX(-80);
  delay(100);
  pumpState(1);

}

void moveY(int steps){
  if (steps >= 0){
    digitalWrite(motorY[0], HIGH); 
    digitalWrite(motorY[1], LOW); 
  }else{
    digitalWrite(motorY[0], LOW); 
    digitalWrite(motorY[1], HIGH); 
  }
  int i;
  for(i = 0; i <= abs(steps); i++){
    delay(step_delayY*5.8);
  }
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
}

void moveX(int steps){
  if (steps >= 0){
    digitalWrite(motorX[0], HIGH); 
    digitalWrite(motorX[1], LOW); 
  }else{
    digitalWrite(motorX[0], LOW); 
    digitalWrite(motorX[1], HIGH); 
  }
  int i;
  for(i = 0; i <= abs(steps); i++){
    delay(step_delayY);
  }
  digitalWrite(motorX[0], LOW); 
  digitalWrite(motorX[1], LOW); 
}

void resetMotors(){
  moveX(500);
  moveY(500);
}

void pumpState(int state){
  if (state == 1){
    digitalWrite(pump[0], HIGH); 
    digitalWrite(pump[1], LOW); 
  }else{
    digitalWrite(pump[0], LOW); 
    digitalWrite(pump[1], LOW); 
  }
}

