/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandBase {
    private boolean finishedAutonomousMoving = false; 
    private boolean finishedAutonomousShooting = false; 
    private double disTraveled = 0.0; 
    private double disNeededToMove = 0.0;  
    private double tiltTime = 0.0; 
    private Timer t; 
    
    public AutonomousCommand(double disNeededToMove, double tiltTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.disNeededToMove = disNeededToMove; 
        this.tiltTime = tiltTime; 
        requires(CommandBase.cameraSubsystem);
        requires(CommandBase.driveSubsystem);
        requires(CommandBase.loaderSubsystem);
        requires(CommandBase.shooterSubsystem); 
        requires(CommandBase.accelerometerSubsytem); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        t = new Timer(); 
        t.start(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   
        //calculate the distance the robot has moved since the Autonomous Command started
        double currentAcc= accelerometerSubsytem.getAcceleration()*9.80665;
        disTraveled = disTraveled + (0.5 * currentAcc * (t.get()/1000000.0)); 
        System.out.println("Robot has moved " + disTraveled + " meters");
        
        if(disTraveled >= disNeededToMove){
            //stops the robot
            driveSubsystem.stop();
            System.out.println("Robot has reached waypoint. Stopping robot");
            finishedAutonomousMoving = true; 
            
            //angle the shooter
            CommandBase.shooterSubsystem.startTilt(1.0);
            Timer.delay(tiltTime); 
            CommandBase.shooterSubsystem.stopTilt();
            
            //starts Shoot command
            CommandGroup g = new CommandGroup(); 
            g.addSequential(new Shoot(1, 4));
            g.start();
            finishedAutonomousShooting = true; 
        }
        else{
            //keep moving the robot
           driveSubsystem.setSpeeds(0.5, 0.5); 
        }
    }
	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("Autonomous Command has been finished"); 
        return finishedAutonomousMoving & finishedAutonomousShooting;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
