package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class Agitator {
	public static Talon talonAgitator = new Talon(4);
	
	public static void run() {
		talonAgitator.set(0);
		
		if (Controllers.gamepad.getRawButton(3) == true) {
			talonAgitator.set(0.3);
		}
		else if (Controllers.gamepad.getRawButton(2) == true) {
			talonAgitator.set(-0.3);
		}
	}
}
