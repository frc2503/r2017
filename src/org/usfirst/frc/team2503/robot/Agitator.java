package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class Agitator {

	public static Talon talonAgitator = new Talon(4);
	
	public static void spin() {
		talonAgitator.set(-0.1);
	}
	
	public static void teleop() {
		if (Controllers.throttle.getRawButton(4) == true) {
			spin();
		}
		else {
			talonAgitator.set(0);
		}
	}
	
	public static void auton() {
		
	}
}
