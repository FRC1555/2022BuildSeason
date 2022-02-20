package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {

	static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	static NetworkTableEntry tx = table.getEntry("tx");
	static NetworkTableEntry ty = table.getEntry("ty");
	static NetworkTableEntry ta = table.getEntry("ta");
	static NetworkTableEntry tv = table.getEntry("tv");
	static NetworkTableEntry led = table.getEntry("ledMode");
	static NetworkTableEntry cam = table.getEntry("camMode");
	static NetworkTableEntry stream = table.getEntry("stream");
	static NetworkTableEntry pipe = table.getEntry("pipeline");

	
	double x = tx.getDouble(0);
	double y = ty.getDouble(0);
	double area = ta.getDouble(0);
	public final static double areaConstant = 60;
	public final static double h1 = 0;
	public final static double h2 = 0;
	public final static double a1 = 0;
	public final static double a2 = 0;
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public double getLimeX() {
		return tx.getDouble(0);
	}
	
	public double getLimeY() {
		return ty.getDouble(0);
	}

	public double getLimeA() {
		return ta.getDouble(0);
	}
	
	public boolean targetVisible() {
		if (tv.getDouble(0) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	/* This function is used to measure the distance of the robot to its target
	h1 is the height from the ground to the center of the outer target 
	h2 is the distance from the ground to the camera
	a is the angle measurement for the camera */
	
	public double distanceByTrig(double H1, double H2, double A) {
		double d;
		d=(H1-H2)/Math.tan(A);
		return d;
	}
	
	public double distanceByArea() {
		return areaConstant/Math.sqrt(getLimeA());
	}
	
	public void ledDefault() {
		led.setNumber(0);
	}
	
	public void ledOn() {
		led.setNumber(3);
	}
	
	public void ledOff() {
		led.setNumber(1);
	}
	
	public void ledBlink() {
		led.setNumber(2);
	}
	
	public void camTrack() {
		cam.setNumber(0);
	}
	
	public void camSee() {
		cam.setNumber(1);
	}
	
	public void setStreamSingle() {
		stream.setNumber(0);
	}
	
	public void setStreamPrimary() {
		stream.setNumber(1);
	}
	
	public void setStreamSecondary() {
		stream.setNumber(2);
	}
	
	public void setPipe(double pipeline) {
		pipe.setNumber(pipeline);
	}
}
