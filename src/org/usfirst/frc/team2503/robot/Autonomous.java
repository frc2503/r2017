package org.usfirst.frc.team2503.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.opencv.core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;

public class Autonomous {
	public static boolean isVisionProcessing = false;
	public static boolean isMovingToTarget = false;
	public static double midpoint = 0.0;
	public static double matWidth = 0.0;
	
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static AnalogInput ultrasonic = new AnalogInput(0);
	private static MatOfKeyPoint matOfBlobs = new MatOfKeyPoint();

	public static Mat processImage(Mat matInput) {
		matWidth = matInput.width();
		
		double[] hsvThresholdHue = {0.0, 180.0};
		double[] hsvThresholdSaturation = {0.0, 255.0};
		double[] hsvThresholdValue = {0.0, 249.0};
		hsvThreshold(matInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, matInput);

		double findBlobsMinArea = 130.0;
		double[] findBlobsCircularity = {0.4, 0.9410774410774411};
		boolean findBlobsDarkBlobs = true;
		findBlobs(matInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, matOfBlobs);
				
		Features2d.drawKeypoints(matInput, matOfBlobs, matInput);
		
		return matInput;
		
		//deliverGear(matOfBlobs);
	}

	private static void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val, Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]), out);
	}
	
	private static void findBlobs(Mat input, double minArea, double[] circularity, Boolean darkBlobs, MatOfKeyPoint blobs) {
		FeatureDetector blobDet = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
		
		try {
			File tempFile = File.createTempFile("config", ".xml");
			StringBuilder config = new StringBuilder();
			config.append("<?xml version=\"1.0\"?>\n");
			config.append("<opencv_storage>\n");
			config.append("<thresholdStep>10.</thresholdStep>\n");
			config.append("<minThreshold>50.</minThreshold>\n");
			config.append("<maxThreshold>220.</maxThreshold>\n");
			config.append("<minRepeatability>2</minRepeatability>\n");
			config.append("<minDistBetweenBlobs>10.</minDistBetweenBlobs>\n");
			config.append("<filterByColor>1</filterByColor>\n");
			config.append("<blobColor>");
			config.append((darkBlobs ? 0 : 255));
			config.append("</blobColor>\n");
			config.append("<filterByArea>1</filterByArea>\n");
			config.append("<minArea>");
			config.append(minArea);
			config.append("</minArea>\n");
			config.append("<maxArea>");
			config.append(Integer.MAX_VALUE);
			config.append("</maxArea>\n");
			config.append("<filterByCircularity>1</filterByCircularity>\n");
			config.append("<minCircularity>");
			config.append(circularity[0]);
			config.append("</minCircularity>\n");
			config.append("<maxCircularity>");
			config.append(circularity[1]);
			config.append("</maxCircularity>\n");
			config.append("<filterByInertia>0</filterByInertia>\n");
			config.append("<filterByConvexity>0</filterByConvexity>\n");
			config.append("</opencv_storage>\n");
			FileWriter writer;
			writer = new FileWriter(tempFile, false);
			writer.write(config.toString());
			writer.close();
			blobDet.read(tempFile.getPath());
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		blobDet.detect(input, blobs);
	}
	
	public static void deliverGear(MatOfKeyPoint blobsMat) {
		System.out.println("Blobs Found: " + blobsMat.total() + " Current Midpoint: " + midpoint + " Gyro: " + gyro.getAngle());
		
		KeyPoint[] blobs = blobsMat.toArray();
		
		if (blobsMat.total() == 2) {
			double x1 = blobs[0].pt.x;
			double x2 = blobs[1].pt.x;
			midpoint = (x1 + x2) / 2;
		}
		
		if (midpoint > matWidth + 25) {
			System.out.println("Greater");
			Drive.drive(0.0, 0.0, 0.75); 
		}
		else if (midpoint < matWidth - 25) {
			System.out.println("Lesser");
			Drive.drive(0.0, 0.0, -0.75);
		}
		else if (ultrasonic.getVoltage() > 1.0) {
			System.out.println("Centered");
			Drive.drive(0.0, 0.5, -0.15);
		}
		else {
			Drive.drive(0.0, 0.0, 0.0);
		}
	}
}