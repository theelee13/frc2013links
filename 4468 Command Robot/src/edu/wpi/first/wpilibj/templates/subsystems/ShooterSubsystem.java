/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;


public class ShooterSubsystem extends Subsystem {
	public static final int MAXRPM = 5000; // TODO: set this to the actual max motor RPM
	
   /*
    * The angle should tell you how far away the shooter is tilted/panned from a reference line
	* angle 0, tilt 0 is parallel to the floor pointing where the chassis is facing.
    */
	
    private double currentTiltAngle = 0.0;
	private double minTiltAngle = 0.0; // TODO: adjust value according to physical constraints
	private double maxTiltAngle = 30.0; // TODO: adjust value according to physical constraints
	
    private double currentPanAngle = 0.0; 
	private double minPanAngle = -30.0; // TODO: adjust value according to physical constraints
	private double maxPanAngle = 30.0; // TODO: adjust value according to physical constraints
	
	// TODO: Make sure these motor controllers are actually Victors (they may be Jags)
	private Victor frontMotor = new Victor(RobotMap.frontShooterMotorChannel);
	private Victor backMotor = new Victor(RobotMap.backShooterMotorChannel);
	private Victor tiltMotor = new Victor(RobotMap.tiltShooterMotorChannel);
	private Victor rotateMotor = new Victor(RobotMap.rotateShooterMotorChannel);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    /**
     * negative degrees will move in one direction and positive degrees will move in another direction
     */
    public void tilt(int degrees){ 
        //bound the number of degrees so that the shooter does not exceed min and max tilt angles
        double requestedPosition = Math.max(minTiltAngle, Math.min(maxTiltAngle, currentTiltAngle + degrees));
	double actualDegrees = requestedPosition - currentTiltAngle;
		
	moveMotor(tiltMotor, (int) actualDegrees);
        currentTiltAngle += actualDegrees; 
    } 
    
	/** 
     * negative degrees will move in one direction and positive degrees will move in another direction
     */
    public void pan(int degrees){
        //bound the number of degrees so that the shooter does not exceed min and max pan angles
		double requestedPosition = Math.max(minPanAngle, Math.min(maxPanAngle, currentPanAngle + degrees));
		double actualDegrees = requestedPosition - currentPanAngle;
		
		moveMotor(rotateMotor, (int) actualDegrees);		
		currentPanAngle += actualDegrees;
    }
	
	/**
	 * Moves the specified motor the specified number of degrees.
	*/
	private void moveMotor(SpeedController motor, int degreesToMove){
		// TODO: If we have a measurement sensor, change this method's signature to take in a 
		// reference to the corresponding sensor for this motor as well and use that to determine 
		// rotation degrees. Until we get a sensor, we will run the motor at 2 RPM for a certain 
		// amount of time... 2 RPM is 12 degrees per second, or 1000/12 milliseconds per degree:
		double millisecondsToMove = degreesToMove*(1000/12);
		motor.set(getSpeedFromRpm(2));
		Timer.delay(millisecondsToMove / 1000); // delay method expects seconds
		motor.set(0);
	}
    
	/**
	 * Sets the shooter motor speeds using target RPM. This is best because the
	 * build team will probably want to think in terms of RPM. Conversion to PWM value is taken
	 * care of by this method.
	*/
    public void setShooterSpeed(int frontRpm, int backRpm){
		if (frontRpm > 1 || frontRpm < 0 || backRpm > 1 || backRpm < 0)
			throw new IllegalArgumentException("RPM values must be in range [0,1]");
			
		double frontSpeed = getSpeedFromRpm(frontRpm);
		double backSpeed = getSpeedFromRpm(backRpm);
		
		frontMotor.set(frontSpeed);
		backMotor.set(backSpeed);
    }
	
	private double getSpeedFromRpm(int rpm){
		// TODO: currently is just a linear function. This will probably change
		// based on the actual motor values, but we will have to wait and see
		if (MAXRPM == rpm)
			return 1;
		else
			return rpm/MAXRPM;
	}
    
    public double getCurrentTilt(){
        return currentTiltAngle; 
    }
    
    public double getCurrentPan(){
        return currentPanAngle; 
    }
}
