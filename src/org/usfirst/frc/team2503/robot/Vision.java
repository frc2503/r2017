package org.usfirst.frc.team2503.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.opencv.core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.*;

public class Vision {
	private static MatOfKeyPoint matOfBlobs = new MatOfKeyPoint();

	public static Mat process(Mat matInput) {
		double[] hsvThresholdHue = {0.0, 180.0};
		double[] hsvThresholdSaturation = {0.0, 255.0};
		double[] hsvThresholdValue = {0.0, 248.56060606060606};
		hsvThreshold(matInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, matInput);

		double findBlobsMinArea = 130.0;
		double[] findBlobsCircularity = {0.48561151079136694, 1.0};
		boolean findBlobsDarkBlobs = true;
		findBlobs(matInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, matOfBlobs);
		
		return matInput;
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
		
		System.out.println(blobs.total());
		
		Features2d.drawKeypoints(input, blobs, input);
	}
}