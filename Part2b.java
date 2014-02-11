package InAtTheDeepEnd;


import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Part2b extends Robot.RobotDemo implements Runnable {
	private final DifferentialPilot pilot;
	private static double angle = 45;
	private static double speed = 150;
	private static double rotSpeed = 150;
	private static int inputState = 0;
	
	
	public Part2b(){
		super();
		this.pilot = super.pilot;
	}
	
	public static void main(String[] args) {
		Button.ENTER.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				
			inputState++;
				
			}

			public void buttonReleased(Button b) {

			}

		});
		Button.RIGHT.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				if (inputState == 0) {
					angle *=2;
				}
				else if(inputState == 1){
					speed *=2;
				}
				else if(inputState == 2){
					rotSpeed *=2;
				}
			}

			public void buttonReleased(Button b) {

			}

		});
		Button.LEFT.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				if (inputState == 0) {
					angle /=2;
				}
				else if(inputState == 1){
					speed /=2;
				}
				else if(inputState == 2){
					rotSpeed /=2;
				}
			}
			public void buttonReleased(Button b) {
			}
		});
		
		
		while(inputState < 3){
			LCD.clear();
			LCD.drawString(angle + " " + speed + " " + rotSpeed, 0, 0);
			Delay.msDelay(100);
		}
		
		Part2 demo = new Part2();
		Button.waitForAnyPress();

		demo.run();

	}

	@Override
	public void run() {

		

		SensorPort port2 = SensorPort.S2;
		SensorPort port3 = SensorPort.S3;
		
		port2.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int aOldValue,
					int aNewValue) {
				if(aOldValue > aNewValue){
					stopTurn();
				}
				
			}

		});
		
		port3.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int aOldValue,
					int aNewValue) {
				if(aOldValue > aNewValue){
					stopTurn();
				}
			}

		});
		pilot.setTravelSpeed(speed);
		pilot.setRotateSpeed(rotSpeed);
		pilot.forward();

		while (true) {
			
		}

	}
	
	private void stopTurn(){
		pilot.stop();
		pilot.travel(-100);
		pilot.rotate(angle);
		pilot.forward();
	}
	
	
}
