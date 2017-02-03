package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	public void robotInit() {
	}

	public void teleopPeriodic() {
		Drive.teleop();
		Shoot.teleop();
	}
	
	public void autonomousPeriodic() {
		Drive.auton();
		Shoot.auton();
	}

}

