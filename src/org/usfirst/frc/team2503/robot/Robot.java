package org.usfirst.frc.team2503.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	Thread visionThread;

	@Override
	public void robotInit() {
		visionThread = new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			
			Mat mat = new Mat();

			CvSource outputStream = CameraServer.getInstance().putVideo("2503 Camera Stream", 640, 480);
			CvSink cvSink = CameraServer.getInstance().getVideo();
			
			 while(!Thread.interrupted()) {
                 cvSink.grabFrame(mat);
                 outputStream.putFrame(mat);
                 
                 Vision.process(mat);
             }
		});
		
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	public void autonomousInit() {
	}
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
	}
	
	public void teleopPeriodic() {
		//BallIntake.teleop();
		BallShooter.teleop();
		//DriveBase.teleop();
		//SuperShifter.teleop();
	}
}

