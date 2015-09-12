String rawData = "";
int count = 0;
int storedX = 0;
int storedY = 0;
int power;

// --------------------------------------------------------------------------- Motors
int motorY[] = {2, 3};
int motorX[] = {5, 6};
int pump[] = {10, 12};
int step_delayY = 1;


void setup() {
  Serial.begin(9600);
 
  // Setup motors
  int i;
  for(i = 0; i < 2; i++){
    pinMode(motorY[i], OUTPUT);
    pinMode(motorX[i], OUTPUT);
    pinMode(pump[i], OUTPUT);
}
  moveX(-100);
  delay(1000);
  moveX(10);
  
  

}

void loop() {
  // Collecting list data from serial monitor
  while (Serial.available()){
    delay (3);
    char c = Serial.read();
    rawData += c;
  }
  Serial.println(rawData);
  
  // Obtain values from the string sent
  if (rawData.length() > 0){
    digitalWrite(13,HIGH);
    String a = rawData.substring(0,rawData.indexOf("|"));
    String b = rawData.substring(rawData.indexOf("|")+1,rawData.length()-2);
    String c = rawData.substring(rawData.length()-1);
    int xCoord = a.toInt();  
    int yCoord = b.toInt(); 
    power = c.toInt(); 
    Serial.println(power);

  // 300 for the length, 100 for the width
  int differenceX = xCoord - storedX;
  int differenceY = yCoord - storedY;

  
  moveX(differenceX);
  moveY(differenceY);
  

  // To get previous values to track location
  storedX = xCoord;
  storedY = yCoord;
  }
  
  // For active pump or inactive pump
  pumpState(power);
  // Clear data collected from serial
  rawData = "";
  delay(100);
}

void moveY(int steps){
  int i;
  int decreasing=0;
  if (steps >= 0){
    digitalWrite(motorY[0], LOW); 
    digitalWrite(motorY[1], HIGH); 
    for(i = 0; i <= abs(steps); i++){
      delay(step_delayY*5.8 - decreasing);
      decreasing += 0.5;}
  }else{
    digitalWrite(motorY[0], HIGH); 
    digitalWrite(motorY[1], LOW); 
    for(i = 0; i <= abs(steps); i++){
      delay(step_delayY*7);}
  }
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
}

void moveX(int steps){
  int i;
  int decreasing =0;
  if (steps >= 0){
    digitalWrite(motorX[0], LOW); 
    digitalWrite(motorX[1], HIGH);
    for(i = 0; i <= abs(steps); i++){
      delay(step_delayY - decreasing);
      decreasing += 0.1; 
  }}else{
    digitalWrite(motorX[0], HIGH); 
    digitalWrite(motorX[1], LOW); 
    for(i = 0; i <= abs(steps); i++){
      delay(step_delayY );
  }


  }
  digitalWrite(motorX[0], LOW); 
  digitalWrite(motorX[1], LOW); 
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


