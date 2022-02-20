// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Systems extends SubsystemBase {
  //System Motors
  public VictorSPX Intake;
  public VictorSPX cargoTransfer;
  public TalonFX Shooter;

  //System Encoders
  public CANCoder ShooterEncoder;
  
  /** Creates a new Systems. */
  public Systems() {
    //System Motor Instantiations
    Intake = new VictorSPX(Constants.Intake_ID);
    cargoTransfer = new VictorSPX(Constants.cargoTransfer_ID);
    Shooter = new TalonFX(Constants.Shooter_ID);

    ShooterEncoder = new CANCoder(Constants.Shooter_ID);
    
  }
  //Utilizing Methods Built in SpecialMethods
  public void IntakeSpeeds(double speed){
    Intake.set(ControlMode.PercentOutput, speed);
  }
  public void CargoTransferSpeeds(double speed){
    cargoTransfer.set(ControlMode.PercentOutput, speed);
  }
  public void ShooterSpeeds(double speed){
    Shooter.set(ControlMode.PercentOutput, speed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

