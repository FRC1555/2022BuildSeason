// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class ClimbingSystem extends SubsystemBase {
  public static TalonFX Shooter;
  public static Solenoid LeftPneumatic;
  public static Solenoid RightPneumatic;
  /** Creates a new ClimbingSystem. */
  public ClimbingSystem() {
    Shooter = new TalonFX(Constants.Shooter_ID);
    LeftPneumatic = new Solenoid(0, PneumaticsModuleType.CTREPCM , Constants.LeftPneumatic_ID);
    RightPneumatic = new Solenoid(1, PneumaticsModuleType.CTREPCM, Constants.RightPneumatic_ID);
  }
  
  public void ShooterSpeeds(double speed){
    Shooter.set(ControlMode.PercentOutput, speed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
