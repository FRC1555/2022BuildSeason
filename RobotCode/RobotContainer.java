// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;
//import javax.swing.text.StyledEditorKit.ForegroundAction;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;  
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
  */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Our Joysticks
  public static final Joystick driverController_1 = new Joystick(Constants.DRIVER_CONTROLLER_1);
  public static final Joystick driverController_2 = new Joystick(Constants.DRIVER_CONTROLLER_2);

  //Our DriveTrain
  public static final DriveTrain driveTrain = new DriveTrain();
  
  //Our SubSystems
  public static final Systems systems = new Systems();
  public static final ClimbingSystem climbingSystem = new ClimbingSystem();

  //Our Autos
  private final Command m_GyroTest = new GyroTest();
  private final Command m_CoordinateTesting = new CoordinateTesting();
  private final Command m_BlueAlpha = new BlueAlpha();  
  private final Command m_BlueBeta = new BlueBeta();
  private final Command m_RedAlpha = new RedAlpha();
  private final Command m_RedBeta = new RedBeta();  

  //Sendable chooser
  public static SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    driveTrain.setDefaultCommand(new TeleOp());
  }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_chooser.setDefaultOption("CoordinateTesting", m_CoordinateTesting);
    m_chooser.addOption("GyroTest", m_GyroTest);
    m_chooser.addOption("BlueAlpha", m_BlueAlpha);
    m_chooser.addOption("BlueBeta",m_BlueBeta);
    m_chooser.addOption("RedAlpha",m_RedAlpha);
    m_chooser.addOption("RedBeta",m_RedBeta);
    SmartDashboard.putData(RobotContainer.m_chooser);
    //Runs the Autonomous
    return m_chooser.getSelected();
    // return null;
  }
}
