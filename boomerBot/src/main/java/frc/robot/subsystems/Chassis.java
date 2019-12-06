/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.arcadeDrive;
/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Spark leftMotorA;
  Spark leftMotorB;
  Spark rightMotorA;
  Spark rightMotorB;
  SpeedControllerGroup right;
  SpeedControllerGroup left;
  DifferentialDrive drive;

  public Chassis(){

    leftMotorA = new Spark(RobotMap.LEFT_MOTOR_A);
    leftMotorB = new Spark(RobotMap.LEFT_MOTOR_B);
    rightMotorA = new Spark(RobotMap.RIGHT_MOTOR_A);
    rightMotorB = new Spark(RobotMap.RIGHT_MOTOR_B);

    right = new SpeedControllerGroup(rightMotorA, rightMotorB);
    left = new SpeedControllerGroup(leftMotorA, leftMotorB);
    drive = new DifferentialDrive(right, left);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new arcadeDrive());
  }

  public void driveWithJoystick(double x, double y){
    drive.arcadeDrive(x, y);
  }

}

