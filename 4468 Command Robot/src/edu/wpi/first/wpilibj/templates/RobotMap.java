package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // Motor channel definitions (PWM Channels:
    public static final int rightDriveChannel = 1;
    public static final int leftDriveChannel = 2;
    public static final int frontShooterMotorChannel = 3;
    public static final int loaderMotorChannel = 4;
    
    // Spike Channels
    public static final int rotateShooterMotorChannel = 1;
    public static final int tiltShooterMotorChannel = 2; 
    public static final int LEDRelay = 3; //NOTE: need it to move forward at ALL times
    
    public static final int panEncoderChannelA = 5;
    public static final int panEncoderChannelB = 6;
    public static final int tiltEncoderChannelA = 7;
    public static final int tiltEncoderChannelB = 8;
     
    //Accelerometer
    public static final int accelerometerPort = 1;  
    
    
    // IO port definitions:
    
    //Joystick ports
    public static final int driveStickPort = 1;
    public static final int shooterStickPort = 2;
    
    //Button ports
    public static final int triggerButtonNumber = 1;
    public static final int leftStartButtonNumber = 8;
    public static final int middleStartButonNumber = 6;
    public static final int rightStartButtonNumber = 9;
    
}
