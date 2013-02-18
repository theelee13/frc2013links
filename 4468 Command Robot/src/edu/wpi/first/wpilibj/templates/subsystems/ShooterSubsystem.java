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
	
   /*
    * The angle should tell you how far away the shooter is tilted/panned from a reference line
	* angle 0, tilt 0 is parallel to the floor pointing where the chassis is facing.
    */
	
    private double currentTiltAngle = 0.0;
	
    private double currentPanAngle = 0.0; 

    private Jaguar frontMotor = new Jaguar(RobotMap.frontShooterMotorChannel);
    private Relay tiltMotor = new Relay(RobotMap.tiltShooterMotorChannel);
    private Relay rotateMotor = new Relay(RobotMap.rotateShooterMotorChannel);
    private Relay LEDRelay = new Relay(RobotMap.LEDRelay); 
    private Encoder panEncoder = new Encoder(RobotMap.panEncoderChannelA, RobotMap.panEncoderChannelB);
    private Encoder tiltEncoder = new Encoder(RobotMap.tiltEncoderChannelA, RobotMap.tiltEncoderChannelB);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        panEncoder.start();
        tiltEncoder.start();
    }
    
    
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
    public void setShooterSpeed(double pwnSpeed){
        if (pwnSpeed > 1.0 || pwnSpeed < -1.0)
                throw new IllegalArgumentException("PwnSpeed must be in range [0,1]");

        frontMotor.set(pwnSpeed);
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
