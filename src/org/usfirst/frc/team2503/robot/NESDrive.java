package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/*
 * The following class sets up a holonomic drive system with a single joystick and the mecanum wheel drive base. It
 * works by getting the vertical, horizontal, and rotational values of the joystick and then storing them as values
 * if they pass a threshold. After that each wheel calculates a final value based of off all inputs.
 */

public class NESDrive {
	
	// Declaing a Joystick
	public static Joystick controller = new Joystick(3);
	
	// Declaring the Talons
	public static Talon frontLeft = new Talon(0);
	public static Talon frontRight = new Talon(2);
	public static Talon backLeft = new Talon(1);
	public static Talon backRight = new Talon(3);
	
	// Declaring Doubles for Controller Values
	public static double ctrlHorizontal = 0;
	public static double ctrlVertical = 0;
	public static double ctrlRotational = 0;
	
	// Declaring Doubles to Send to Talons
	public static double valVertical = 0;
	public static double valHorizontal = 0;
	public static double valRotational = 0;
	
	// Declaring a Controller Movement Threshold
	public static double valThreshold = .15;
	
	// Drive Function
	public static void drive(double ctrlHorizontal, double ctrlVertical, double ctrlRotational) {
		
		// If the Controller Value is Greater than the Threshold set it as the Motor Value
		if (ctrlVertical > valThreshold || ctrlVertical < -valThreshold) {
		    valVertical = ctrlVertical;
		}
		else {
		    valVertical = 0;
		}
		
		// If the Controller Value is Greater than the Threshold set it as the Motor Value
		if (ctrlHorizontal > valThreshold || ctrlHorizontal < -valThreshold) {
		    valHorizontal = ctrlHorizontal;
		}
		else {
		    valHorizontal = 0;
		}
		
		// If the Controller Value is Greater than the Threshold set it as the Motor Value
		if (ctrlRotational > valThreshold || ctrlRotational < -valThreshold) {
		    valRotational = ctrlRotational;
		}
		else {
		    valRotational = 0;
		}

		//Remote Control Commands
		frontLeft.set(valVertical + valRotational + valHorizontal);
		frontRight.set(-(valVertical - valRotational - valHorizontal));
		backLeft.set(valVertical + valRotational - valHorizontal);
		backRight.set(-(valVertical - valRotational + valHorizontal)); 
		
	}
	
	public static void teleop() {
		ctrlHorizontal = 0.0;
		ctrlVertical = 0.0;
		ctrlRotational = 0.0;
		
		if (controller.getRawAxis(1) > 0.1) {
			ctrlHorizontal = 0.75;
		}
		else if (controller.getRawAxis(1) < -0.1) {
			ctrlHorizontal = -0.75;
		}
		
		if (controller.getRawAxis(4) > 0.1) {
			ctrlVertical = -0.75;
		}
		else if (controller.getRawAxis(4) < -0.1) {
			ctrlVertical = 0.75;
		}
		
		if (controller.getRawButton(2) == true) {
			ctrlRotational = 0.75;
		}
		else if (controller.getRawButton(3) == true) {
			ctrlRotational = -0.75;
		}
		
		// Send the Controller Values to the Drive Function
		drive(ctrlHorizontal, ctrlVertical, ctrlRotational);
		
	}
	
	public static void auton() {
		
		// Drive Forward
		drive(0.0, 0.25, 0.0);
		
	}
}
