package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shifter {
	public static Compressor compressor = new Compressor(0);
	public static DoubleSolenoid shiftController = new DoubleSolenoid(0, 1);

	public static void run() {
		compressor.setClosedLoopControl(true);
		shiftController.set(DoubleSolenoid.Value.kForward);
		
		if (Controllers.throttle.getRawButton(5) == true) {
			shiftController.set(DoubleSolenoid.Value.kReverse);
		}
		else if(Controllers.throttle.getRawButton(6) == true){
			shiftController.set(DoubleSolenoid.Value.kForward);
		}	
	}	
}
