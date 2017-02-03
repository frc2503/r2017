package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;

public class Shoot {
	
	public static Joystick joystick = new Joystick(1);
	public static Talon talon = new Talon(7);
	public static Double motorSpeed = 0.0;

	public static void shoot() {
		
		motorSpeed = joystick.getRawAxis(2);
		System.out.println("Motor Speed: " + motorSpeed);
		
		talon.set(motorSpeed);
		
	}	
	
	public static void stop() {
		
		talon.set(0);
		
	}
	
	public static void teleop() {	

		if (joystick.getRawButton(2) == true) 
		{
			shoot();
		}
		else
		{
			stop();
		}
 
	}
	
	public static void auton() {
		
		shoot();
		
	}
	
}