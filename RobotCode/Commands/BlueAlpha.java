// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.SpecialMethods;
import frc.robot.IanTimer;

//Auto For Top Left (Blue Alliance)
public class BlueAlpha extends CommandBase {
  private boolean finished;
  /** Creates a new AutoAlpha. */
  public BlueAlpha() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SpecialMethods.UniversalReset();
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Timer Instantiation
    IanTimer ianTimer = new IanTimer();
    //The robot angle must be preadjusted so the calculations are correct
    RobotContainer.driveTrain.navX.setAngleAdjustment(-30);    

    //Phase 1: Go to Ball and Intake
    RobotContainer.systems.IntakeSpeeds(1);
    SpecialMethods.CoordinateWizard(280,197,193,242);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();
    //Phase 2: Finalize intaking the ball and turning back
    ianTimer.start();
    while(ianTimer.currentMills() >= 1000){
      RobotContainer.systems.IntakeSpeeds(1);
    }
    RobotContainer.systems.IntakeSpeeds(0);
  
    SpecialMethods.CoordinateWizard(193,242,278,185);
    
    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();
    //Phase 3: Scoring the ball
    SpecialMethods.RPMShooter(300);

    //Phase 4: Turning and leaving the Tarmac
    SpecialMethods.CoordinateWizard(278,185,185,177);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();
    
    finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SpecialMethods.UniversalReset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}


