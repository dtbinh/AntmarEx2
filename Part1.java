package InAtTheDeepEnd;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Part1 extends Robot.RobotDemo implements Runnable {

	private static int state;

	public void run() {

		Button.RIGHT.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				if (state < 8) {
					state++;
				}
			}

			public void buttonReleased(Button b) {

			}

		});
		Button.LEFT.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				if (state > 2) {
					state--;
				}
			}
			public void buttonReleased(Button b) {
			}
		});

		state = 4;
		final DifferentialPilot pilot = super.pilot;
		
		LCD.drawString("Hello World", 0, 0);
		
		Button.waitForAnyPress();
		
		while (true) {
			pilot.travel(200);
			pilot.rotate(360 / state);
			LCD.clear();
			LCD.drawString("" + state, 0, 0);
			Delay.msDelay(200);
		}

	}

	public static void main(String[] args) {

		Button.waitForAnyPress();

		Part1 demo = new Part1();
		demo.run();

	}

}
