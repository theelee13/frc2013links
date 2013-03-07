package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.templates.subsystems.*;
import edu.wpi.first.wpilibj.Timer; 
public class Shoot extends CommandBase {
    
    private double speedPwm = 0.0; 
    private boolean finishedFiring = false; 
    private int numberOfTimesToShoot = 0; 
    
    //NOTE: We should get sensors for the shooter to detect is a Frisbee has been shot or not. This can prevent 
    //the Frisbees from jamming.
	
    public Shoot(double pwm, int n) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        speedPwm = pwm; 
        numberOfTimesToShoot = n; 
        requires(CommandBase.loaderSubsystem);
        requires(CommandBase.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Starting shooter motor at" + speedPwm + " PWM"); 
    	CommandBase.shooterSubsystem.setShooterSpeed(speedPwm);
        System.out.println("Waiting for motor to spin...");
        Timer.delay(5.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Loading"); 
        CommandBase.loaderSubsystem.loadFrisbee(); //loads the Frisbee
        System.out.println("Shooting"); 
        numberOfTimesToShoot--; 
        if(numberOfTimesToShoot <= 0){
            finishedFiring = true; 
        }
        else{
            finishedFiring = false; 
        }
        Timer.delay(1.0); 
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finishedFiring; 
    }

    // Called once after isFinished returns true
    protected void end() {
	CommandBase.loaderSubsystem.reset();
	CommandBase.shooterSubsystem.setShooterSpeed(0.0); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
