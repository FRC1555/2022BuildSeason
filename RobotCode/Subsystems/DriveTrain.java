// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {

  //Drive Motors
  CANSparkMax motorLeftFront;
  CANSparkMax motorLeftBack;
  CANSparkMax motorRightFront;
  CANSparkMax motorRightBack;
  //Drive Encoders
  public RelativeEncoder motorLeftFrontEncoder;
  public RelativeEncoder motorLeftBackEncoder;
  public RelativeEncoder motorRightFrontEncoder;
  public RelativeEncoder motorRightBackEncoder;
  //Caster Wheel Encoders
  public Encoder CasterLeft;
  public Encoder CasterRight; 
  

  //Mecanum Class Instantiation
  MecanumDrive mecanumDrive;

  //Nav X instantiation
  public AHRS navX;

  //Ultrasonic Sensor Instantiation
  //public AnalogInput JimboTheUltrasonicSensor;

  public DriveTrain(){
  //Motor Instantiation
  motorRightBack = new CANSparkMax(Constants.motorRightBack_ID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless); 
  motorRightFront = new CANSparkMax(Constants.motorRightFront_ID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
  motorLeftFront = new CANSparkMax(Constants.motorLeftFront_ID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
  motorLeftBack = new CANSparkMax(Constants.motorLeftBack_ID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);

  //Encoder Instantiation
  motorRightBackEncoder = motorRightBack.getEncoder();
  motorRightFrontEncoder = motorRightFront.getEncoder();
  motorLeftFrontEncoder = motorLeftFront.getEncoder();
  motorLeftBackEncoder = motorLeftBack.getEncoder();

  CasterLeft = new Encoder(Constants.CasterLeft_ID_A, Constants.CasterLeft_ID_B);
  CasterRight = new Encoder(Constants.CasterRight_ID_A, Constants.CasterRight_ID_B);

  //Inverts Motors For Mecanum
  motorLeftFront.setInverted(false);
  motorLeftBack.setInverted(false);
  motorRightFront.setInverted(true);
  motorRightBack.setInverted(true);

  //Sensor Instantiation
  navX = new AHRS(Constants.navX_ID);
  //JimboTheUltrasonicSensor = new AnalogInput(Constants.JimboTheUltrasonicSensor_ID);

  //MecanumDrive Method Instantiation
  mecanumDrive = new MecanumDrive(motorLeftFront,motorLeftBack,motorRightFront,motorRightBack);
  }
  //Used for Telecommunicated Operations (TeleOP)
  public void mecanumDrive(double ySpeed, double xSpeed, double zRotation){
    mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  
  //Used for Autonomous
  public void setLeftMotors(double speed){
    motorLeftFront.set(speed);
    motorLeftBack.set(speed);
  }
  public void setRightMotors(double speed){
    motorRightFront.set(speed);
    motorRightBack.set(speed);
  }
  @Override
  public void periodic() {
    SmartDashboard.putBoolean("navx connected", navX.isConnected());
    // This method will be called once per scheduler run
  }
}
