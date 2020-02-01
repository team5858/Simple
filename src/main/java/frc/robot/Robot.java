/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Robot extends TimedRobot {
  
  TalonSRX leftMaster, leftFollower;
  TalonSRX rightMaster, rightFollower;

  //TalonSRX shooter;

  double turn, throttle;
  double left, right;
  final double turnStrength = 0.85;

  Joystick controller;


  public void robotInit() {
    this.leftMaster = new TalonSRX(3);
    this.leftFollower = new TalonSRX(4);
    this.rightMaster = new TalonSRX(1);
    this.rightFollower = new TalonSRX(2);

    this.leftFollower.set(ControlMode.Follower, 3);
    this.rightFollower.set(ControlMode.Follower, 1);
    
    this.leftMaster.setInverted(true);
    this.rightMaster.setInverted(true);



    //this.shooter = new TalonSRX(1);

    this.controller = new Joystick(0);

    
  }
  

  public void teleopPeriodic() {
    this.left = this.controller.getRawAxis(1);
    this.right = this.controller.getRawAxis(4);

    /*
    * this manually does the math for arcade drive given the two trigger inputs
    * because the Differential drive class doesnt want to work with TalonSRX 
    */
    this.turn = (Math.abs(this.right) <= 0.1)? 0. : this.right*this.turnStrength;
    this.throttle = (Math.abs(this.left) <= 0.12)? 0 : this.left;

    this.leftMaster.set(ControlMode.PercentOutput, this.throttle + turn);
    this.rightMaster.set(ControlMode.PercentOutput, -this.throttle + turn);

    
    /*if(this.controller.getRawButton(2)){
      this.shooter.set(ControlMode.PercentOutput, 1);
    }
    if(this.controller.getRawButton(4)){
      this.shooter.set(ControlMode.PercentOutput, 0);
    }*/
  }

  
}
