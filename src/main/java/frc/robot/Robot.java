/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  
  WPI_TalonSRX leftMaster, leftFollower;
  WPI_TalonSRX rightMaster, rightFollower;

  final double turnStrength = 0.85;

  Joystick controller;
  DifferentialDrive drivetrain;

  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();

    this.leftMaster = new WPI_TalonSRX(3);
    this.leftFollower = new WPI_TalonSRX(4);
    this.rightMaster = new WPI_TalonSRX(1);
    this.rightFollower = new WPI_TalonSRX(2);

    this.leftFollower.set(ControlMode.Follower, 3);
    this.rightFollower.set(ControlMode.Follower, 1);
    
    this.leftMaster.setInverted(false);
    this.rightMaster.setInverted(false);
    this.leftFollower.setInverted(false);
    this.rightFollower.setInverted(false);

    this.drivetrain = new DifferentialDrive(this.leftMaster, this.rightMaster);
    this.drivetrain.setMaxOutput(0.5);

    this.controller = new Joystick(0);
  }
  
  public void teleopPeriodic() {
    double speed = -1 * this.controller.getRawAxis(1);
    double rotation = this.controller.getRawAxis(4);

    //this.leftMaster.set(ControlMode.PercentOutput, -1.0);
    //this.rightMaster.set(ControlMode.PercentOutput, -1.0);

    this.drivetrain.arcadeDrive(speed, rotation);
    
    /*if(this.controller.getRawButton(2)){
      this.shooter.set(ControlMode.PercentOutput, 1);
    }
    if(this.controller.getRawButton(4)){
      this.shooter.set(ControlMode.PercentOutput, 0);
    }*/
  }

  
}
