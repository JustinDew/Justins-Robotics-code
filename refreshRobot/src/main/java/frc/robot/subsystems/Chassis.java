/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.joystickDrive;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Spark frontMotorA;
  Spark backMotorA;
  Spark frontMotorB;
  Spark backMotorB;
  SpeedControllerGroup leftSide;
  SpeedControllerGroup rightSide;
  DifferentialDrive drive;

  public Chassis() {

    frontMotorA = new Spark(RobotMap.FRONT_MOTOR_A);
    backMotorA = new Spark(RobotMap.BACK_MOTOR_A);
    frontMotorB = new Spark(RobotMap.FRONT_MOTOR_B);
    backMotorB = new Spark(RobotMap.BACK_MOTOR_B);

    leftSide = new SpeedControllerGroup(frontMotorA, backMotorA);
    rightSide = new SpeedControllerGroup(frontMotorB, backMotorB);

    drive = new DifferentialDrive(leftSide, rightSide);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new joystickDrive());

  }

  public void driveWithJoystick(Joystick leftStick, Joystick rightStick) {
    drive.arcadeDrive(leftStick.getY(), rightStick.getX());
  }

  /*public void tankDrive(Joystick leftSide, Joystick rightSide) {
    drive.tankDrive(leftSide.getY(), rightSide.getY());
  }*/
}
