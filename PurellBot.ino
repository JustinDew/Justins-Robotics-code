//Imports
#include  <PS4BT.h>
#include <usbhub.h>
#ifdef dobogusinclude
#include <spi4teensy3.h>
#endif
#include <SPI.h>

//Global variables
USB Usb;
BTD Btd(&Usb);
PS4BT PS4(&Btd, PAIR);
bool printAngle, printTouch;
uint8_t oldL2Value, oldR2Value;
int ENA = 9;
int IN1 = 8;
int IN2 = 7;
int ENB = 6;
int IN3 = 5;
int IN4 = 4;
int toggle = 1;
int motorSpeed;

//Initial setup code
void setup() {
//PS4 bluetooth controller connection (borrowed from USB Sheild 2.0 library example code)
  Serial.begin(115200);
#if !defined(__MIPSEL__)
  while (!Serial); // Wait for serial port to connect - used on Leonardo, Teensy and other boards with built-in USB CDC serial connection
#endif
  if (Usb.Init() == -1) {
    Serial.print(F("\r\nOSC did not start"));
    while (1); // Halt
  }
  Serial.print(F("\r\nPS4 Bluetooth Library Started"));

//Sets the motors as outputs 
  pinMode(ENA, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENB, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
}

//Code ran repeatedly
void loop() {
  Usb.Task();
  if(PS4.connected()){
    tankDrive();
    if(PS4.getButtonClick(CROSS)){
     honk();
    }
  }
}
/*
 * Function for tank style driving. 
 * Y-axis of the left joystick controls left side and Y-axis on the right joystick controls right side.
 * Pressing "R1" will double the speed of the motors.
*/
void tankDrive(){
  if(PS4.getButtonClick(R1)){
    toggle *= -1;
  }
  if(toggle == 1){
    motorControl(PS4.getAnalogHat(LeftHatY), IN1, IN2, ENA, 0.5);
    motorControl(PS4.getAnalogHat(RightHatY), IN3, IN4, ENB, 0.5); 
  }else{
    motorControl(PS4.getAnalogHat(LeftHatY), IN1, IN2, ENA, 1.0);
    motorControl(PS4.getAnalogHat(RightHatY), IN3, IN4, ENB, 1.0); 
  } 
}

/*
 * Converts value of joystick to control the speed and direction of the motor
 *  axis - the axis that will control the motor
 *  p1 - first input to control the motor
 *  p2 - second input to control the motor
 *  EN - input to enable the motor
 *  speedm - double that multiplies with the speed of the motor
*/
void motorControl(int axis, int p1, int p2, int EN, double speedm){
  if(axis < 97){
    digitalWrite(p1, HIGH);
    digitalWrite(p2, LOW);
    motorSpeed = map(((axis - 97) * -1), 0, 97, 0, 255);
  }
  else if(axis > 137){
    digitalWrite(p1, LOW);
    digitalWrite(p2, HIGH);
    motorSpeed = map(axis, 137, 255, 0, 255);
  }
  else{
    motorSpeed = 0;
  }
  analogWrite(EN, motorSpeed * speedm);
}
/*
 * Sets the motor to a speed too low to actually move and makes a buzzing sound
 * buzzes for 500 ms
 * simply used to get attention
 */
void honk(){
  for(int i = 0; i < 5; i++){
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);
    analogWrite(ENA, 40);
    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
    analogWrite(ENB, 40);
    delay(100);
  }
}
