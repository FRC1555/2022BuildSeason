// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.SpecialMethods;
import frc.robot.IanTimer;

//Auto For Top Left (Blue Alliance)
public class BlueBeta extends CommandBase {
  private boolean finished;
  /** Creates a new AutoAlpha. */
  public BlueBeta() {
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
    IanTimer ianTimer = new IanTimer();
    RobotContainer.driveTrain.navX.setAngleAdjustment(-155);  
    
    //Phase 1: Rotate and go to ball
    RobotContainer.systems.IntakeSpeeds(1);
    SpecialMethods.CoordinateWizard(277, 111, 198 ,75);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    //Phase 2: Pick up ball, then rotate to hub
    ianTimer.start();
    while(ianTimer.currentMills() >= 1000){
      RobotContainer.systems.IntakeSpeeds(1);
    }
    RobotContainer.systems.IntakeSpeeds(0);
    SpecialMethods.CoordinateWizard(198 ,75, 271, 68);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    //Phase 3: Line up shot
    SpecialMethods.CoordinateWizard(271, 68, 276, 83);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    //Phase 4: Fire then exit tarmac
    SpecialMethods.RPMShooter(60);
    SpecialMethods.CoordinateWizard(276, 83, 250,52);

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
