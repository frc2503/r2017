package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Intake {

	public static Joystick joystick = new Joystick(1);
	public static Talon talon = new Talon(5);
	public static Double motorSpeed = 0.0;

	public static void run(String direction) {
		
		if (direction == "inwards") {
			talon.set(1);
		}
		else if (direction == "outwards") {
			talon.set(-1);
		}
		
	}	
	
	public static void stop() {
		
		talon.set(0);
		
	}
	
	public static void teleop() {	

		if (joystick.getRawButton(3) == true) 
		{
			run("inwards");
		}
		else if (joystick.getRawButton(4) == true) {
			run("outwards");
		}
		else
		{
			stop();
		}
 
	}
	
	public static void auton() {
		
	}
}
