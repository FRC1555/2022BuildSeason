// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Motor Constants (Set up for CAN BUS Order)
    public static int motorRightBack_ID = 1;
    public static int motorRightFront_ID = 2;
    public static int motorLeftFront_ID = 3;
    public static int motorLeftBack_ID = 4;
    public static int shooter_ID = 5;
    public static int intake_ID = 6;
    public static int cargoTransfer_ID = 7;
    public static int climberWinch_ID = 8;
    
    
    //Sensors (PWM and MXP)
    public static final I2C.Port navX_ID = I2C.Port.kMXP;
    //public static final int JimboTheUltrasonicSensor_ID = 0;
    public static final int CasterLeft_ID_A = 1;
    public static final int CasterLeft_ID_B = 2;
    public static final int CasterRight_ID_A = 3;
    public static final int CasterRight_ID_B = 4;
    public static final int WinchEncoder_ID_A = 5;
    public static final int WinchEncoder_ID_B = 6;


    //Driver Controller Constants
    public static final int DRIVER_CONTROLLER_1 = 0;
    public static final int DRIVER_CONTROLLER_2 = 1;

    //DIO Outputs (Solenoids and Limit Switch)
    public static final int intakePneumatic_ID_A = 0;
    public static final int intakePneumatic_ID_B = 1;
    public static final int climbingPneumatic_ID_A = 2; 
    public static final int climbingPneumatic_ID_B = 3;
    


    
}

