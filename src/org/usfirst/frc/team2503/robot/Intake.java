package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class Intake {
	public static Talon talonIntake = new Talon(6);
	
	public static void run() {	
		if (Controllers.gamepad.getRawButton(4) == true)  
		{
			talonIntake.set(1);
		}
		else if (Controllers.gamepad.getRawButton(8) == true) {
			talonIntake.set(-1);
		}
		else {
			talonIntake.set(0);
		}
	}
}
