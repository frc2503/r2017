package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class Shifter {
	
	// Declaring Components
	public static Compressor compressor = new Compressor(0);
	public static DoubleSolenoid shiftController = new DoubleSolenoid(0, 1);
	public static Joystick joystick = new Joystick(1);
	public static String shifterState = "forward";	
	
	// Set the Shifter to Forward On Start
	public static void init() {
		shiftController.set(DoubleSolenoid.Value.kForward);
		compressor.setClosedLoopControl(true);
	}

	// Await Shift Input During Teleop
	public static void teleop() {
		if (joystick.getRawButton(6) == true) {
			//shift();
			shiftController.set(DoubleSolenoid.Value.kReverse);
		}
		else if(joystick.getRawButton(2) == true){
			shiftController.set(DoubleSolenoid.Value.kForward);
		}	
	}
	
	// Shift from Current Position to the Opposite Position
	public static void shift() {
		if (shifterState == "forward") {
			shiftController.set(DoubleSolenoid.Value.kReverse);
			shifterState = "reverse";
			System.out.println("Shifted Successfully!");
		}
		else if (shifterState == "reverse") {
			shiftController.set(DoubleSolenoid.Value.kForward);
			shifterState = "forward";
			System.out.println("Shifted Successfully!");
		}
		else {
			System.out.println("Failed to Shift!");
		}
		
	}
	
}
