// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.SpecialMethods;
import frc.robot.IanTimer;

//Auto For Top Left (Blue Alliance)
public class RedBeta extends CommandBase {
  private boolean finished;
  /** Creates a new AutoAlpha. */
  public RedBeta() {
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
    
    //Phase 1: Rotate and go to ball (Two Step motion unlike the others)
    RobotContainer.systems.IntakeSpeeds(1);
    SpecialMethods.CoordinateWizard(352, 198, 356 ,218);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    SpecialMethods.CoordinateWizard(356,218,447,251);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    //Phase 2: Pick up ball, then rotate to hub
    ianTimer.start();
    while(ianTimer.currentMills() >= 1000){
      RobotContainer.systems.IntakeSpeeds(1);
    }
    RobotContainer.systems.IntakeSpeeds(0);

    SpecialMethods.CoordinateWizard(447, 251, 355, 221);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    //Phase 3: Line up shot
    SpecialMethods.CoordinateWizard(355,221, 350, 199);

    SpecialMethods.EncoderReset();
    SpecialMethods.MotorReset();

    //Phase 4: Fire then exit tarmac
    SpecialMethods.RPMShooter(300);

    SpecialMethods.CoordinateWizard(350, 199, 407, 284);

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
