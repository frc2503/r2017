package org.usfirst.frc.team2503.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	Thread visionThread;

	public void robotInit() {
		visionThread = new Thread(() -> {
			
			// Create  a Camera Server 
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			
			// Set the Camera Resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			//CvSink cvSink = CameraServer.getInstance().getVideo();
		});
		
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	public void autonomousInit() {
		
	}
	
	public void autonomousPeriodic() {
		Drive.auton();
		Intake.auton();
		Shooter.auton();
	}
	
	public void teleopInit() {
		Shifter.init();
	}
	

	public void teleopPeriodic() {
		Drive.teleop();
		Intake.teleop();
		Shooter.teleop();
		Shifter.teleop();
	}
}

