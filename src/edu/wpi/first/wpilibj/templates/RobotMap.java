package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // Motor channel definitions:
    public static final int rightDriveChannel = 1;
    public static final int leftDriveChannel = 2;
	
	public static final int frontShooterMotorChannel = 3;
	public static final int backShooterMotorChannel = 4;
	public static final int tiltShooterMotorChannel = 5;
	public static final int rotateShooterMotorChannel = 6;
	
	public static final int loaderMotorChannel = 7;
    
	// IO port definitions:
	public static final int driveStickPort = 1;
}
