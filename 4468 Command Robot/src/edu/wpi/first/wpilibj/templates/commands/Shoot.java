package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.templates.subsystems.*;/


public class Shoot extends CommandBase {
    private boolean fired = false; 
    
    //NOTE: We should get sensors for the shooter to detect is a Frisbee has been shot or not. This can prevent 
    //the Frisbees from jamming.
	
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.loaderSubsystem);
        requires(CommandBase.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize(int frontRPM, int backRPM) {
    	CommandBase.ShooterSubsystem.setShooterSpeed(frontRPM, backRPM);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(double degrees) {
        CommandBase.LoaderSubsystem.loadFrisbee(degrees); //loads the Frisbee
        fired = true; //Note: should we add a sensor to make sure the Frisbees do not get jammed? 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return fired; 
    }

    // Called once after isFinished returns true
    protected void end() {
	    CommandBase.LoaderSubsystem.reset(degrees); //resets the arms back into the original position
	    CommandBase.ShooterSubsystem.setShooterSpeed(0, 0); //stops the motors
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
