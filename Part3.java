package InAtTheDeepEnd;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Part3 extends Robot.RobotDemo implements Runnable {

	private final DifferentialPilot pilot;
	private final TouchSensor touchLeft = new TouchSensor(SensorPort.S2);
	private final TouchSensor touchRight = new TouchSensor(SensorPort.S4);
	private int counter = 0;

	final UltrasonicSensor usSensor = new UltrasonicSensor(SensorPort.S1);

	public Part3() {
		super();
		this.pilot = super.pilot;
	}

	public static void main(String[] args) {

		Button.waitForAnyPress();

		Part3 demo = new Part3();
		demo.run();

	}

	@Override
	public void run() {

		SensorPort port2 = SensorPort.S2;
		SensorPort port3 = SensorPort.S4;

		port2.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int aOldValue,
					int aNewValue) {
				if (aOldValue > aNewValue) {
					hitAWall(false);
				}

			}

		});

		port3.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int aOldValue,
					int aNewValue) {
				if (aOldValue > aNewValue) {
					hitAWall(true);
				}
			}

		});
		pilot.forward();
		while (true) {

		}
	}

	public void hitAWall(boolean right) {
		pilot.stop();
		Delay.msDelay(500);
		if (counter < 5) {
			if (right == true) {
				if (!touchLeft.isPressed()) {
					shuffleRight();
					counter++;
				} else {
					decide();
				}
			}

			if (right == false) {
				if (!touchRight.isPressed()) {
					shuffleLeft();
					counter++;
				} else {
					decide();
				}
			}
		} else {
			pilot.travel(-250);
			decide();
		}

		pilot.forward();

	}

	public void shuffleRight() {
		pilot.travel(-56);
		pilot.rotate(45);
	}

	public void shuffleLeft() {
		pilot.travel(-56);
		pilot.rotate(-45);
	}

	public void decide() {
		counter = 0;
		pilot.travel(-70);
		int distLeft = 0;
		int distRight = 0;

		pilot.rotate(-90);

		for (int i = 0; i < 10; i++) {
			Delay.msDelay(20);
			distRight += usSensor.getDistance();
		}
		distRight = distRight / 10;

		pilot.rotate(180);

		for (int i = 0; i < 10; i++) {
			Delay.msDelay(20);
			distLeft += usSensor.getDistance();
		}
		distLeft = distLeft / 10;

		if (distLeft < distRight) {
			pilot.rotate(-185);
		}

	}
}
