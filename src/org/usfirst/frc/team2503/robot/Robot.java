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
                 
                 if (Autonomous.isVisionProcessing == true) {
                	 outputStream.putFrame(Autonomous.processImage(image));
                 }
                 else {
                	 outputStream.putFrame(image);
                 }
             }
		});
		
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	public void autonomousInit() {
		Autonomous.isVisionProcessing = true;
		Autonomous.gyro.reset();
	}
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
		Autonomous.isVisionProcessing = false;
		Shifter.compressor.setClosedLoopControl(true);
		
	}
	
	public void teleopPeriodic() {
		System.out.println("value: " + Autonomous.ultrasonic.getValue());
		System.out.println("voltage: " + Autonomous.ultrasonic.getVoltage());
		Agitator.run();
		Drive.run();
		Intake.run();
		Shifter.run();
		Shooter.run();
	}
}

