package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shifter {
	public static Compressor compressor = new Compressor(0);
	public static DoubleSolenoid shiftController = new DoubleSolenoid(0, 1);
		
	public static void run() {
		System.out.println("Compressor Value: " + compressor.getCompressorCurrent());
		
		if (compressor.getCompressorCurrent() > 8.15) {
			//compressor.setClosedLoopControl(false);
		}

		if (Controllers.gamepad.getRawButton(9)) {
			shiftController.set(DoubleSolenoid.Value.kReverse);
		}
		else if(Controllers.gamepad.getRawButton(10) == true){
			shiftController.set(DoubleSolenoid.Value.kForward);
		}	
	}	
}
