package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class Drive {
	public static Talon frontLeft = new Talon(0);
	public static Talon frontRight = new Talon(2);
	public static Talon backLeft = new Talon(1);
	public static Talon backRight = new Talon(3);

	public static double ctrlHorizontal = 0;
	public static double ctrlVertical = 0;
	public static double ctrlRotational = 0;

	public static double valVertical = 0;
	public static double valHorizontal = 0;
	public static double valRotational = 0;

	public static double valThreshold = .05;
	
	public static void teleop() {
		ctrlHorizontal = Controllers.throttle.getRawAxis(0); 
		ctrlVertical = Controllers.throttle.getRawAxis(1) * -1; 
		ctrlRotational = Controllers.throttle.getRawAxis(3);
		
		ctrlHorizontal *= Math.abs(ctrlHorizontal);
		ctrlVertical *= Math.abs(ctrlVertical);
		ctrlRotational *= Math.abs(ctrlRotational);
		
		drive(ctrlHorizontal, ctrlVertical, ctrlRotational);
	}
	
	public static void auton() {
		drive(0.0, 0.25, 0.0);
	}
	
	public static void drive(double ctrlHorizontal, double ctrlVertical, double ctrlRotational) {
		if (ctrlVertical > valThreshold || ctrlVertical < -valThreshold) {
		    valVertical = ctrlVertical * Math.abs(ctrlVertical);
		}
		else {
		    valVertical = 0;
		}

		if (ctrlHorizontal > valThreshold || ctrlHorizontal < -valThreshold) {
			valHorizontal = ctrlHorizontal  *Math.abs(ctrlHorizontal);
		}
		else {
		    valHorizontal = 0;
		}

		if (ctrlRotational > valThreshold || ctrlRotational < -valThreshold) {
			valRotational = ctrlRotational * Math.abs(ctrlRotational) / 2;
		}
		else {
		    valRotational = 0;
		}

		frontLeft.set(valVertical + valRotational + valHorizontal);
		frontRight.set(-(valVertical - valRotational - valHorizontal));
		backLeft.set(valVertical + valRotational - valHorizontal);
		backRight.set(-(valVertical - valRotational + valHorizontal)); 	
	}
}
