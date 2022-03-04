// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class ClimbingSystem extends SubsystemBase {
  //Defining all systems
  public PneumaticsControlModule ThePCM;
  public Compressor PCMCompressor;
  public Compressor PHCompressor;
  public DoubleSolenoid IntakeSolenoid;
  public DoubleSolenoid ClimbingSolenoid;
  public VictorSPX ClimberWinch;
  public Encoder WinchEncoder;

  /** Creates a new ClimbingSystem. */
  public ClimbingSystem() {
    /*Instantiating Pneumatics Control Module (Controls Solenoids) 
    and Pneumatics Hub (Controls Compressor)*/
    PCMCompressor = new Compressor(9, PneumaticsModuleType.CTREPCM);
    PHCompressor = new Compressor(10, PneumaticsModuleType.REVPH);

    //PCMCompressor.enableDigital();
    //PHCompressor.disable();

    // boolean enabled = PCMCompressor.enabled();
    // boolean pressureSwitch = PCMCompressor.getPressureSwitchValue();
    // double current = PHCompressor.getCurrent();

    DoubleSolenoid IntakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    DoubleSolenoid ClimbingSolenoid= new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

    ClimberWinch = new VictorSPX(Constants.climberWinch_ID);

    WinchEncoder = new Encoder(Constants.WinchEncoder_ID_A, Constants.WinchEncoder_ID_B);


    ClimberWinch.setInverted(true);
  }
  
  public void ClimberWinch(double speed){
    ClimberWinch.set(ControlMode.PercentOutput, speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
