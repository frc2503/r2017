package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class BallIntake {
	public static Talon talonIntake = new Talon(5);
	
	public static void teleop() {	
		if (Controllers.throttle.getRawButton(2) == true)  
		{
			talonIntake.set(1);
		}
		else {
			talonIntake.set(0);
		}
	}
	
	public static void auton() {
	}
}
