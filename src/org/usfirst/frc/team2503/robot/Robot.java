package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	public void robotInit() {
	}
	
	public void autonomousInit() {
	}
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
	}
	
	public void teleopPeriodic() {
		Drive.teleop();
		Intake.teleop();
		Shooter.teleop();
		//Shifter.teleop();
	}
}

