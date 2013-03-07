/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class AutonomousCommand extends CommandBase {
    private boolean finishedFiring = false; 
    private boolean finishedAutonomous = false; 
    private double secondsSinceStart = 0.0; 
    
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.cameraSubsystem);
        requires(CommandBase.driveSubsystem);
        requires(CommandBase.loaderSubsystem);
        requires(CommandBase.shooterSubsystem); 
        requires(CommandBase.accelerometerSubsytem); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {     
        if(!(finishedAutonomous)){ 
            moveToDesignatedSpot(2.54); 
            
            
            
        }
        if(finishedAutonomous){
            //rotate
            moveToDesignatedSpot(5.30); 
        }

        //move back to the feeder station
        //delay the robot for several seconds so that players can load the frisbee
        //repeats execute(), but the distanceNeededToMove changes
        //distance from starting point to designated location is different from distance from
        //designated location and feeder station
        
        
    }
    
    private void moveToDesignatedSpot(double distanceNeededToMove){
        while(true){
            double currentAcceleration = accelerometerSubsytem.getAcceleration()*9.80665;
            double distanceTraveled = (1/2)*currentAcceleration* (secondsSinceStart*secondsSinceStart);
            System.out.println("Robot has moved " + distanceTraveled + " meters"); 
            if(distanceTraveled >= distanceNeededToMove){
                System.out.println("Robot has reached waypoint. Stopping"); 
                driveSubsystem.stop(); 
                break; 
            }
            Timer.delay(0.02);  
        }
            
    }
	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("Autonomous Command has been finished"); 
        return finishedAutonomous;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
