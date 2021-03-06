package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

//Welcome to Brendan's Playground
public class SpecialMethods {
    //CPR = Counts per Revolution
    private static final double cprNEO = 42;
    private static final double cprTHROUGHBORE = 8192;
    private static final double cprWINCH = 1024;
    //whd = Wheel Diameter in inches
    private static final double whdNEO = 6; 
    private static final double whdTHROUGHBORE = 4;
    private static final double whdWINCH = 4;
    //Ticks 2 inches conversion: Basically outputs how many inches are in one tick
    public static final double Tick2InchesNeo = Math.PI*whdNEO/cprNEO;
    public static final double Tick2InchesCasters = Math.PI*whdTHROUGHBORE/cprTHROUGHBORE;
    public static final double Tick2InchesWinch = Math.PI*whdWINCH/cprWINCH;
    //Various motor speeds
    public static final double slow = 0.1;
    public static final double medium = 0.5;
    public static final double fast = 0.8;
    public static final double fullThrottle = 1; 
    //Button States
    enum Cutton1{IN,OUT};

    public static Cutton1 state = Cutton1.IN;

    //Custom Resets
    public static void UniversalReset(){
        RobotContainer.driveTrain.navX.reset();
        RobotContainer.driveTrain.setLeftMotors(0);
        RobotContainer.driveTrain.setRightMotors(0);  
        RobotContainer.systems.IntakeSpeeds(0);        
        RobotContainer.systems.CargoTransferSpeeds(0);    
        RobotContainer.systems.ShooterSpeeds(0);        
        RobotContainer.driveTrain.motorLeftFrontEncoder.setPosition(0);
        RobotContainer.driveTrain.motorLeftBackEncoder.setPosition(0);
        RobotContainer.driveTrain.motorRightFrontEncoder.setPosition(0);
        RobotContainer.driveTrain.motorRightBackEncoder.setPosition(0);
    }

    public static void EncoderReset(){
        RobotContainer.driveTrain.motorLeftFrontEncoder.setPosition(0);
        RobotContainer.driveTrain.motorLeftBackEncoder.setPosition(0);
        RobotContainer.driveTrain.motorRightFrontEncoder.setPosition(0);
        RobotContainer.driveTrain.motorRightBackEncoder.setPosition(0);
        RobotContainer.driveTrain.CasterLeft.reset();
        RobotContainer.driveTrain.CasterRight.reset();
    }

    public static void MotorReset(){
        RobotContainer.driveTrain.setLeftMotors(0);
        RobotContainer.driveTrain.setRightMotors(0);  
        RobotContainer.systems.ShooterSpeeds(0);   
    }

    public static void SensorReset(){
        RobotContainer.driveTrain.navX.reset();
    }

    
    /*Coordinate Wizard (AKA Brendan's Baby) is a method that takes in 2 Coordinates and calculates the trajectory of
    the robot that it needs to take by vizualizing every possible coordinate as a Right-Angle Triangle. On a 2D cartesian
    plane, we can use trigonometry and the distance formula in order to calculate how far the robot needs to turn and how 
    far the robot needs to move.*/
    public static void CoordinateWizard(double X1, double Y1, double X2, double Y2){
        //Angle Calculations
        double calculateMoveDistanceX = (X2-X1);
        double calculateMoveDistanceY = (Y2-Y1);
        double tangentTheta = Math.atan(calculateMoveDistanceX/calculateMoveDistanceY);
        double tangentThetaDegrees = Math.toDegrees(tangentTheta);

        //Distance Calculation using Trigonometric Distance Formula in inches
        double moveDistance = Math.sqrt(Math.pow(calculateMoveDistanceX,2) + Math.pow(calculateMoveDistanceY,2));
        double moveInTicks = moveDistance/Tick2InchesNeo;

        //Get RobotAngle
        double robotTheta = RobotContainer.driveTrain.navX.getAngle();
        //Logic: Avoid resetting encoders on accident when it gets off course
        int correctionCount = 0;

        //If Coordinate is to the right of the robot
        if (robotTheta < tangentThetaDegrees){
            while (robotTheta < tangentThetaDegrees){
                robotTheta = RobotContainer.driveTrain.navX.getAngle();
                RobotContainer.driveTrain.setLeftMotors(slow);
                RobotContainer.driveTrain.setRightMotors(-slow);    
            }

            correctionCount = correctionCount + 1;
        }
        //If Coordinate is to the left of the robot
        if (robotTheta > tangentThetaDegrees){
            while (robotTheta > tangentThetaDegrees){
                robotTheta = RobotContainer.driveTrain.navX.getAngle();
                RobotContainer.driveTrain.setLeftMotors(-slow);
                RobotContainer.driveTrain.setRightMotors(slow);  
            }

            correctionCount = correctionCount + 1;
        }
        
        //Logic: Avoid resetting encoders on accident when it gets off course
        if (correctionCount <= 1){
            MotorReset(); 
            EncoderReset(); 
        }
        //Robot goes to Coordinate
        while (-RobotContainer.driveTrain.motorLeftFrontEncoder.getPosition() < moveInTicks &&
        -RobotContainer.driveTrain.motorLeftBackEncoder.getPosition() < moveInTicks &&
        RobotContainer.driveTrain.motorRightFrontEncoder.getPosition() < moveInTicks &&
        RobotContainer.driveTrain.motorRightBackEncoder.getPosition() < moveInTicks){

            RobotContainer.driveTrain.setLeftMotors(slow);
            RobotContainer.driveTrain.setRightMotors(slow);            
        }      
    }

    //Helps correct the robot to compensate for incompetent hardware and weight imbalances
    public static void TeleWithGyroCorrection(){
        double gyro =  RobotContainer.driveTrain.navX.getAngle();
        double GyroCorrectionleftStickY = -RobotContainer.driverController_1.getY();
        double GyroCorrectionrightStickX = RobotContainer.driverController_1.getX();

        //Custom deadband necessary since you can never have a perfect angle. 
        if(GyroCorrectionleftStickY < -0.05 || GyroCorrectionleftStickY > 0.05){
            SensorReset();
            while(gyro <= -5 || gyro >= 5) {
                gyro =  RobotContainer.driveTrain.navX.getAngle();
                //Adjusts Left
                if (gyro >= 5) {
                RobotContainer.driveTrain.setLeftMotors(slow);
                }
                //Adjusts Right
                if (gyro <= -5){
                RobotContainer.driveTrain.setRightMotors(slow);
                }
            }
        }
        if(GyroCorrectionrightStickX < -0.05 || GyroCorrectionrightStickX > 0.05){
            SensorReset();
            while(gyro <= -5 || gyro >= 5) {
                gyro =  RobotContainer.driveTrain.navX.getAngle();
                //Adjusts Left
                if (gyro >= 5) {
                RobotContainer.driveTrain.setLeftMotors(slow);
                }
                //Adjusts Right
                if (gyro <= -5){
                RobotContainer.driveTrain.setRightMotors(slow);
                }
            }
        }
    }

    //Method for Intake Controls
    public static void IntakeControls(boolean inTrigger, boolean outTrigger){
        if(inTrigger == true){
            RobotContainer.systems.IntakeSpeeds(1);
        }
        if(outTrigger == true){
            RobotContainer.systems.IntakeSpeeds(-1);
        }
        else{
            RobotContainer.systems.IntakeSpeeds(0);
        }
    }
    //Method for Cargo Transfer Controls
    public static void CargoTransferControls(boolean inButton, boolean outButton){
        if(inButton == true){
            RobotContainer.systems.CargoTransferSpeeds(1);
        }
        if(outButton == true){
            RobotContainer.systems.CargoTransferSpeeds(-1);
        }
        else{
            RobotContainer.systems.CargoTransferSpeeds(0);
        }
    }
    //Method for Shooter Controls
    public static void ShooterControls(Boolean shootButton1, Boolean shootButton2){
        if(shootButton1 == true && shootButton2 == true){
            RobotContainer.systems.ShooterSpeeds(-0.1);
        }
        else{
            RobotContainer.systems.ShooterSpeeds(0);
        }
    }
    
    //Function that switches the solenoids for the climber manually with a button
    public static void ManualClimbingControl(Boolean Cutton){
        //This timer setup is used so that the button doesn't cause a massive amount of spam
        IanTimer ianTimer = new IanTimer();
        ianTimer.start();
        double Delay = 500;

        switch (state){
            //Sets Solenoids OFF
            // case OFF:
            //     if(Cutton && ianTimer.currentMills() > Delay){
            //         state = Cutton1.IN;
            //         RobotContainer.climbingSystem.IntakeSolenoid.set(Value.kOff);
            //         ianTimer.reset();
            //     }
            //     break;
            //Sets Solenoids IN
            case IN:
                if(Cutton && ianTimer.currentMills() > Delay){
                    state = Cutton1.OUT;
                    RobotContainer.climbingSystem.IntakeSolenoid.set(Value.kForward);
                    ianTimer.reset();
                }
                break;

            //Sets Solenoid Out
            case OUT:
                if(Cutton && ianTimer.currentMills() > Delay){
                    state = Cutton1.IN;
                    RobotContainer.climbingSystem.IntakeSolenoid.set(Value.kReverse);
                    ianTimer.reset();
                }
                break;
            
        }
    }

    //Function that switches solenoid positions in Auto
    public static void ClimbingControl(String Position){
        switch (state){
            // //Sets Solenoids OFF
            // case OFF:
            //     if(Position == "Off"){
            //         state = Cutton1.IN;
            //         RobotContainer.climbingSystem.IntakeSolenoid.set(Value.kOff);
            //     }
            //     break;
            //Sets Solenoids IN
            case IN:
                if(Position == "Forward"){
                    state = Cutton1.OUT;
                    RobotContainer.climbingSystem.IntakeSolenoid.set(Value.kForward);
                }
                break;
            //Sets Solenoid Out
            case OUT:
                if(Position == "Reverse"){
                    state = Cutton1.IN;
                    RobotContainer.climbingSystem.IntakeSolenoid.set(Value.kReverse);
                }
                break;
        }
    }
    //Function that switches the solenoids for the intake manually with a button
    public static void IntakeLiftControl(Boolean Cutton){
        //This timer setup is used so that the button doesn't cause a massive amount of spam
        IanTimer ianTimer = new IanTimer();
        ianTimer.start();
        double Delay = 500;

        switch (state){
            //Sets Solenoids OFF
            // case OFF:
            //     if(Cutton && ianTimer.currentMills() > Delay){
            //         state = Cutton1.IN;
            //         RobotContainer.climbingSystem.ClimbingSolenoid.set(Value.kOff);
            //         ianTimer.reset();
            //     }
            //     break;
            //Sets Solenoids IN
            case IN:
                if(Cutton && ianTimer.currentMills() > Delay){
                    state = Cutton1.OUT;
                    RobotContainer.climbingSystem.ClimbingSolenoid.set(Value.kForward);
                    ianTimer.reset();
                }
                break;
            //Sets Solenoid Out
            case OUT:
                if(Cutton && ianTimer.currentMills() > Delay){
                    state = Cutton1.IN;
                    RobotContainer.climbingSystem.ClimbingSolenoid.set(Value.kReverse);
                    ianTimer.reset();
                }
                break;
            
        }
    }

    //Function that takes RPM and revs up the shooter until it can 
    public static void RPMShooter(double DesiredRPM){
        IanTimer ianTimer = new IanTimer();  
        double speed = 0;
        boolean check = false; 

        //Math converting RPM to Encoder Ticks per 100ms
        double encoderCount = 4096;
        double ticksPer100ms = (DesiredRPM*encoderCount)/600;

        //Only transfers cargo into shooter if the shooter is running at a certain velocity
        while(check = false){
            //Velocity Detection Logic
            if(RobotContainer.systems.Shooter.getSelectedSensorVelocity() >= ticksPer100ms){
                //Cargo Transfer then SHOOT!!!
                ianTimer.reset();
                while(ianTimer.currentMills() <= 3000){
                    RobotContainer.systems.CargoTransferSpeeds(1);
                    RobotContainer.systems.ShooterSpeeds(speed);
                } 
                //Exit Loop
                check = true;
            }
            //Speed Increment
            speed = speed + 0.01;
            RobotContainer.systems.ShooterSpeeds(speed);
        }
        //Turn off shooter
        RobotContainer.systems.ShooterSpeeds(0);
    }

    public static void CoordinateWizardWithCasters(double X1, double Y1, double X2, double Y2){
        //Angle Calculations
        double calculateMoveDistanceX = (X2-X1);
        double calculateMoveDistanceY = (Y2-Y1);
        double tangentTheta = Math.atan(calculateMoveDistanceX/calculateMoveDistanceY);
        double tangentThetaDegrees = Math.toDegrees(tangentTheta);

        //Distance Calculation using Trigonometric Distance Formula
        double moveDistance = Math.sqrt(Math.pow(calculateMoveDistanceX,2) + Math.pow(calculateMoveDistanceY,2));
        double moveInTicks = moveDistance/Tick2InchesCasters;

        //Get RobotAngle
        double robotTheta = RobotContainer.driveTrain.navX.getAngle();

        //Logic: Avoid resetting encoders on accident when it gets off course
        int correctionCount = 0;

        //If Coordinate is to the right of the robot
        if (robotTheta < tangentThetaDegrees){
            while (robotTheta < tangentThetaDegrees){
                robotTheta = RobotContainer.driveTrain.navX.getAngle();
                RobotContainer.driveTrain.setLeftMotors(slow);
                RobotContainer.driveTrain.setRightMotors(-slow);    
            }

            correctionCount = correctionCount + 1;
        }
        //If Coordinate is to the left of the robot
        if (robotTheta > tangentThetaDegrees){
            while (robotTheta > tangentThetaDegrees){
                robotTheta = RobotContainer.driveTrain.navX.getAngle();
                RobotContainer.driveTrain.setLeftMotors(-slow);
                RobotContainer.driveTrain.setRightMotors(slow);  
            }

            correctionCount = correctionCount + 1;
        }
        
        //Logic: Avoid resetting encoders on accident when it gets off course
        if (correctionCount <= 1){
            MotorReset(); 
            EncoderReset(); 
        }

        //Robot goes to Coordinate
        while (-RobotContainer.driveTrain.CasterLeft.get() < moveInTicks &&
        RobotContainer.driveTrain.CasterRight.get() < moveInTicks){

            RobotContainer.driveTrain.setLeftMotors(fast);
            RobotContainer.driveTrain.setRightMotors(fast);            
        }      
    }

    public static void TheAscent() {
        SpecialMethods.UniversalReset();

        RobotContainer.climbingSystem.ClimbingSolenoid.set(Value.kForward);

        double winchingDistance = 36;
        double winchingDistanceinTicks = Tick2InchesWinch * winchingDistance;

        if (winchingDistanceinTicks > RobotContainer.climbingSystem.WinchEncoder.get()){

        }
    }

    //WORK IN PROGRESS
    public static void CoordinateWizardWithLocalization(double X2, double Y2){
         //Angle Calculations
         double calculateMoveDistanceX = (X2);
         double calculateMoveDistanceY = (Y2);
         double tangentTheta = Math.atan(calculateMoveDistanceX/calculateMoveDistanceY);
         double tangentThetaDegrees = Math.toDegrees(tangentTheta);
 
         //Distance Calculation using Trigonometric Distance Formula in inches
         double moveDistance = Math.sqrt(Math.pow(calculateMoveDistanceX,2) + Math.pow(calculateMoveDistanceY,2));
         double moveInTicks = moveDistance/Tick2InchesNeo;
 
         //Get RobotAngle
         double robotTheta = RobotContainer.driveTrain.navX.getAngle();
         //Logic: Avoid resetting encoders on accident when it gets off course
         int correctionCount = 0;
 
         //If Coordinate is to the right of the robot
         if (robotTheta < tangentThetaDegrees){
             while (robotTheta < tangentThetaDegrees){
                 robotTheta = RobotContainer.driveTrain.navX.getAngle();
                 RobotContainer.driveTrain.setLeftMotors(slow);
                 RobotContainer.driveTrain.setRightMotors(-slow);    
             }
 
             correctionCount = correctionCount + 1;
         }
         //If Coordinate is to the left of the robot
         if (robotTheta > tangentThetaDegrees){
             while (robotTheta > tangentThetaDegrees){
                 robotTheta = RobotContainer.driveTrain.navX.getAngle();
                 RobotContainer.driveTrain.setLeftMotors(-slow);
                 RobotContainer.driveTrain.setRightMotors(slow);  
             }
 
             correctionCount = correctionCount + 1;
         }
         
         //Logic: Avoid resetting encoders on accident when it gets off course
         if (correctionCount <= 1){
             MotorReset(); 
             EncoderReset(); 
         }
         //Robot goes to Coordinate
         while (-RobotContainer.driveTrain.motorLeftFrontEncoder.getPosition() < moveInTicks &&
         -RobotContainer.driveTrain.motorLeftBackEncoder.getPosition() < moveInTicks &&
         RobotContainer.driveTrain.motorRightFrontEncoder.getPosition() < moveInTicks &&
         RobotContainer.driveTrain.motorRightBackEncoder.getPosition() < moveInTicks){
 
             RobotContainer.driveTrain.setLeftMotors(slow);
             RobotContainer.driveTrain.setRightMotors(slow);            
         }      

    }
}


