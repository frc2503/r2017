package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;

public class Shooter {
	
	public static Joystick joystick = new Joystick(1);
	public static Talon talon = new Talon(7);
	public static Double motorSpeed = 0.0;

	public static void initalize() {
		
	}

	public static void run() {
		talon.set(0);
		
		motorSpeed = joystick.getRawAxis(2);
		System.out.println("Motor Speed: " + motorSpeed);
		
		if (joystick.getRawButton(2) == true) {
			talon.set(-motorSpeed);
		}
 
	}
	
}