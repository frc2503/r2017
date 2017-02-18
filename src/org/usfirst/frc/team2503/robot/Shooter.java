package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	public static Talon talonShooter = new Talon(5);
		
	public static void run() {	
		if (Controllers.gamepad.getRawButton(1) == true)
		{
			talonShooter.set(Controllers.throttle.getRawAxis(2));
		}
		else
		{
			talonShooter.set(0);
		}
	}
}
