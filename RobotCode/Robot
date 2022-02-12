// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MecDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
    SpecialMethods.UniversalReset();
    SmartDashboard.putData(RobotContainer.m_chooser);
  }
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    
    //Clockwise = Negative; Counter Clockwise = Positive
    SmartDashboard.putNumber("Get Angle (Turning Left and Right)", RobotContainer.driveTrain.navX.getAngle());
    //Roll Right = Positive; Roll Left = Negative    
    SmartDashboard.putNumber("Get Roll (Rolling Left or Right)", RobotContainer.driveTrain.navX.getRoll());
    //Nose Up = Positive; Nose Down = Negative
    SmartDashboard.putNumber("Get Pitch (Nose Up or Down)", -RobotContainer.driveTrain.navX.getPitch());
    //Encoder Values
    SmartDashboard.putNumber("LeftFrontEncoder", -RobotContainer.driveTrain.motorLeftFrontEncoder.getPosition());    
    SmartDashboard.putNumber("LeftBackEncoder", -RobotContainer.driveTrain.motorLeftBackEncoder.getPosition());
    SmartDashboard.putNumber("RightFrontEncoder", RobotContainer.driveTrain.motorRightFrontEncoder.getPosition());
    SmartDashboard.putNumber("RightBackEncoder", RobotContainer.driveTrain.motorRightBackEncoder.getPosition());
    SmartDashboard.putNumber("Shooter", RobotContainer.systems.Shooter.getSelectedSensorVelocity());
    //Jimbo Readings
    SmartDashboard.putNumber("JimboReadings", RobotContainer.driveTrain.JimboTheUltrasonicSensor.getValue());
    CommandScheduler.getInstance().run();
  }
  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}
  @Override
  public void disabledPeriodic() {}
  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  
  public void autonomousInit() {
    m_autonomousCommand = robotContainer.getAutonomousCommand();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  
  }
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    RobotContainer.driveTrain.setDefaultCommand(new MecDrive());
  }
  /** This function is called periodically during operator control. */

  @Override
  public void testPeriodic() {}
}
