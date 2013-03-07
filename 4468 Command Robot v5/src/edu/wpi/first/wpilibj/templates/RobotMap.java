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
    
    
    // Spike Channels
    public static final int loaderMotorChannel = 7; 
    public static final int rotateShooterMotorChannel = 5;
    public static final int tiltShooterMotorChannel = 6; 
    public static final int LEDRelay = 4; //NOTE: need it to move forward at ALL times
    
    public static final int panEncoderChannelA = 1;
    public static final int panEncoderChannelB = 2;
    public static final int tiltEncoderChannelA = 3;
    public static final int tiltEncoderChannelB = 4;
     
    //Accelerometer
    public static final int accelerometerPort = 1;  
    
    
    // IO port definitions:
    
    //Joystick ports
    public static final int driveStickPort = 1;
    public static final int shooterStickPort = 2;
    
    
    //Button ports
    //Drive joystick button ports
    
    public static final int loadButtonNumber = 1; 
    
    public static final int emergencyBreakButtonNumber = 2; 
    //must be always kept pressed for the robot to stop completely
    
    public static final int emergencyEndAutonomousButtonNumber = 11; 
    
    public static final int getCurrentStatsButtonNumber = 10; 
    
    
    
    //Shooter joystick button ports
    public static final int startShooterMotorButtonNumber = 1;
    //must be always kept pressed for the robot to start the motor up
    //if you let go, the motors will slow down gradually to speed zero
    
    public static final int emergencyShooterStopButtonNumber = 2; 
    
    public static final int startOptimizationButtonNumber = 3; 
    
    public static final int stopOpTButton = 4; 
    
    public static final int emergencyStopTiltButton = 8;
    public static final int emergencyStopPanButton = 9; 
            
    public static final int increaseShooterSpeedButtonNumber = 11; 
    public static final int decreaseShooterSpeedButtonNumber = 10; 
    //when either one is pressed, the speed changes by 0.2
    
    public static final int emergencyStopFiringButton = 7; 
    
}
