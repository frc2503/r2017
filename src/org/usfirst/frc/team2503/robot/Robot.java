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
			
			Mat image = new Mat();

			CvSource outputStream = CameraServer.getInstance().putVideo("2503 Camera Stream", 640, 480);
			CvSink cvSink = CameraServer.getInstance().getVideo();
			
			 while(!Thread.interrupted()) {
                 cvSink.grabFrame(image);
                 outputStream.putFrame(image);
                 
                 if (Autonomous.isVisionProcessing == true) {
                	 Autonomous.processImage(image);
                 }
             }
		});
		
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	public void autonomousInit() {
		Autonomous.isVisionProcessing = true;
	}
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
		Autonomous.isVisionProcessing = false;
	}
	
	public void teleopPeriodic() {
		Agitator.run();
		Drive.run();
		Intake.run();
		//Shifter.run();
		Shooter.run();
	}
}

