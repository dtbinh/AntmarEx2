package InAtTheDeepEnd;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;


public class RobotDemo {
	
	public final DifferentialPilot pilot;
	
	public RobotDemo(){
		final RegulatedMotor left = Motor.C;
		final RegulatedMotor right = Motor.B;
		final double wheelDiameter = 56.0;
		final double trackWidth = 120.0;
		pilot = new DifferentialPilot(wheelDiameter,trackWidth, left, right);
		
		Button.ESCAPE.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				System.exit(0);
			}

			public void buttonReleased(Button b) {

			}

		});
	}
	
	
	
}
