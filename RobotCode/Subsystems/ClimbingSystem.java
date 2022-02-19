// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;

public class ClimbingSystem extends SubsystemBase {
  public DoubleSolenoid IntakeSolenoid;
  public DoubleSolenoid ClimbingSolenoid;
 // public static VictorSPX ClimberWinch;
  
  /** Creates a new ClimbingSystem. */
  public ClimbingSystem() {
    IntakeSolenoid = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM , Constants.IntakePneumatic_ID_A, Constants.IntakePneumatic_ID_B);
    ClimbingSolenoid = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbingPneumatic_ID_A, Constants.ClimbingPneumatic_ID_B);
    //ClimberWinch = new VictorSPX(Constants.ClimberWinch_ID);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
