package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Drive {
	
	public static Joystick controller = new Joystick(1);
	
	public static Talon frontLeft = new Talon(1);
	public static Talon frontRight = new Talon(3);
	public static Talon backLeft = new Talon(2);
	public static Talon backRight = new Talon(4);
	
	public static double ctrlHorizontal;
	public static double ctrlVertical;
	public static double ctrlRotational;
	
	public static double valVertical;
	public static double valHorizontal;
	public static double valRotational;
	
	public static double valThreshold;
	
	public static void initialize () {
		ctrlHorizontal = 0;
		ctrlVertical = 0;
		ctrlRotational = 0;
		
		valVertical = 0;
		valHorizontal = 0;
		valRotational = 0;
		
		valThreshold = 0;
		
	}
	
	public static void run () {
		
		ctrlHorizontal = controller.getRawAxis(0); 
		ctrlVertical = -controller.getRawAxis(1); 
		ctrlRotational = controller.getRawAxis(3);
		
		ctrlHorizontal *= Math.abs(ctrlHorizontal);
		ctrlVertical *= Math.abs(ctrlVertical);
		ctrlRotational *= Math.abs(ctrlRotational);
		
		if (ctrlVertical > valThreshold || ctrlVertical < -valThreshold) {
		    valVertical = ctrlVertical;
		}
		else {
		    valVertical = 0;
		}
		
		if (ctrlHorizontal > valThreshold || ctrlHorizontal < -valThreshold) {
		    valHorizontal = ctrlHorizontal;
		}
		else {
		    valHorizontal = 0;
		}
		
		if (ctrlRotational > valThreshold || ctrlRotational < -valThreshold) {
		    valRotational = ctrlRotational;
		}
		else {
		    valRotational = 0;
		}
		
		System.out.println(ctrlHorizontal);
		System.out.println(ctrlVertical);
		System.out.println(ctrlRotational);

		//Remote Control Commands
		frontLeft.set(valVertical + valRotational + valHorizontal);
		frontRight.set(-(valVertical - valRotational - valHorizontal));
		backLeft.set(valVertical + valRotational - valHorizontal);
		backRight.set(-(valVertical - valRotational + valHorizontal));		
	}
}
