package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Talon;

public class BallShooter {
	public static Double motorSpeed = 0.0;
	public static Talon talonShooter = new Talon(4);
		
	public static void teleop() {	
		if (Controllers.throttle.getRawButton(1) == true)
		{
			shoot();
		}
		else
		{
			talonShooter.set(0);
		}
	}
	
	public static void auton() {
		shoot();
	}
	
	public static void shoot() {
		motorSpeed = (Controllers.throttle.getRawAxis(2));
		talonShooter.set(motorSpeed);
		System.out.println(motorSpeed);
	}	
}
