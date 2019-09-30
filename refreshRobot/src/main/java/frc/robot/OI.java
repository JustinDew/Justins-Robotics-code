/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

  private Joystick leftStick;
  private Joystick rightStick;

  public OI() {
    leftStick = new Joystick(RobotMap.LEFT_STICK);
    rightStick = new Joystick(RobotMap.RIGHT_STICK);

  }

public Joystick getLeftStick(){
  return leftStick;
}
public Joystick getRightStick(){
  return rightStick;
}




// Ignor this
/*
  public double getLeftY() {
    double leftY = leftStick.getY();
    if (Math.abs(leftY) > 0.1) {
      return leftY;
    } else {
      return 0;
    }
  }

  public double getRightX() {
    double rightX = rightStick.getX();
    if (Math.abs(rightX) > 0.1) {
      return rightX;
    } else {
      return 0;
    }

  }
*/
}


