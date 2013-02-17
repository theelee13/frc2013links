/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;


public class ShooterSubsystem extends Subsystem {
    public static final int MAXRPM = 5000; // TODO: set this to the actual max motor RPM
	
   /*
    * The angle should tell you how far away the shooter is tilted/panned from a reference line
	* angle 0, tilt 0 is parallel to the floor pointing where the chassis is facing.
    */
	
    private double currentTiltAngle = 0.0;
	
    private double currentPanAngle = 0.0; 

    private Jaguar frontMotor = new Jaguar(RobotMap.frontShooterMotorChannel);
    private Jaguar backMotor = new Jaguar(RobotMap.backShooterMotorChannel);
    private Relay tiltMotor = new Relay(RobotMap.tiltShooterMotorChannel);
    private Relay rotateMotor = new Relay(RobotMap.rotateShooterMotorChannel);
    private Encoder panEncoder = new Encoder(RobotMap.panEncoderChannelA, RobotMap.panEncoderChannelB);
    private Encoder tiltEncoder = new Encoder(RobotMap.tiltEncoderChannelA, RobotMap.tiltEncoderChannelB);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        panEncoder.start();
        tiltEncoder.start();
    }
    
    
    /** NOT NEEDED
    private double currentDistance;
    private double tiltTicksPerSecond = 3000;

    public void tilt(int degrees){ 
        //bound the number of degrees so that the shooter does not exceed min and max tilt angles
        double requestedPosition = Math.max(minTiltAngle, Math.min(maxTiltAngle, currentTiltAngle + degrees));
        double actualDegrees = requestedPosition - currentTiltAngle;
	
        double liftArmLength = 0;
        double screwLength = 0;
        double rampLength = 0;
        
        double startingTicks = tiltEncoder.get();
        tiltMotor.set(Relay.Value.kOn);
        
        double revs = 
        
        double num = Math.pow(-(liftArmLength), 2) + Math.pow(screwLength - currentDistance, 2) + Math.pow(rampLength, 2);
        double den = 2 * (screwLength - currentDistance) * rampLength;
        
        double currentDegreesTilt = Math.acos(num / den);
        
        
    } 
    */
    
    /** 
     * negative degrees will move in one direction and positive degrees will move in another direction
     */
    public void pan(int degrees){
        double ticksAtStart = panEncoder.get();
        double ticksCountedSinceStart = 0;
        while(true){
            if(degrees > 0){
                rotateMotor.setDirection(Relay.Direction.kForward);
            } else {
                rotateMotor.setDirection(Relay.Direction.kReverse);
            }
            
            rotateMotor.set(Relay.Value.kOn);
            
            ticksCountedSinceStart = panEncoder.get() - ticksAtStart;
            double howManyDegreesMoved = ticksCountedSinceStart / 1.742;
            
            if(howManyDegreesMoved >= degrees){
                rotateMotor.set(Relay.Value.kOff);
                break;
            }
        }
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
        if (MAXRPM < rpm)
            return 1;
        else
            return rpm/MAXRPM;
    }
    
    public void startTilt(double direction){
        if (direction > 0) {
            tiltMotor.setDirection(Relay.Direction.kForward);
        } else {
            tiltMotor.setDirection(Relay.Direction.kReverse);
        }
        tiltMotor.set(Relay.Value.kOn);
    }
    
    public void startPan(double direction){
        if (direction > 0) {
            rotateMotor.setDirection(Relay.Direction.kForward);
        } else {
            rotateMotor.setDirection(Relay.Direction.kReverse);
        }
        rotateMotor.set(Relay.Value.kOn);
    }
    
    public void stopPan(){
        rotateMotor.set(Relay.Value.kOff);
    }
    
    public void stopTilt(){
        tiltMotor.set(Relay.Value.kOff);
    }
    
    public double getCurrentTilt(){
        return currentTiltAngle; 
    }
    
    public double getCurrentPan(){
        return currentPanAngle; 
    }
}
