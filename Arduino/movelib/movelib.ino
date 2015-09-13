// --------------------------------------------------------------------------- Motors
int motorY[] = {2, 3};
int motorX[] = {5, 6};
int pump[] = {10, 12};
int step_delayY = 1;


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

}

// --------------------------------------------------------------------------- Loop
void loop() { 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], HIGH); 
  digitalWrite(motorY[1], LOW); 
  delay(100);
  digitalWrite(motorY[0], LOW); 
  digitalWrite(motorY[1], LOW); 

  digitalWrite(motorX[1], HIGH); 
  digitalWrite(motorY[1], HIGH); 
  delay(10);
  digitalWrite(motorX[1], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(10);
  digitalWrite(motorX[1], HIGH); 
  digitalWrite(motorY[1], HIGH); 
  delay(10);
  digitalWrite(motorX[1], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(10);
  digitalWrite(motorX[1], HIGH); 
  digitalWrite(motorY[1], HIGH); 
  delay(10);
  digitalWrite(motorX[1], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(10);
  digitalWrite(motorX[1], HIGH); 
  digitalWrite(motorY[1], HIGH); 
  delay(10);
  digitalWrite(motorX[1], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(10);
  digitalWrite(motorX[1], HIGH); 
  digitalWrite(motorY[1], HIGH); 
  delay(10);
  digitalWrite(motorX[1], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(10);
  digitalWrite(motorX[1], HIGH); 
  digitalWrite(motorY[1], HIGH); 
  delay(10);
  digitalWrite(motorX[1], LOW); 
  digitalWrite(motorY[1], LOW); 
  delay(10);
  
  

  
  delay(1000000);
}
