/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.OptimizeTrajectory;
import edu.wpi.first.wpilibj.templates.commands.Shoot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand();

        // Initialize all subsystems
        CommandBase.init();
        
        
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
        
        OI.leftDrivejoystick.setAxisChannel(Joystick.AxisType.kX, RobotMap.rightDriveChannel);
        OI.leftDrivejoystick.setAxisChannel(Joystick.AxisType.kY, RobotMap.leftDriveChannel);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        // Driving
        double forwardValue = OI.leftDrivejoystick.getY();
        double rightValue = OI.leftDrivejoystick.getX();
        
        double leftDriveSpeed = Math.max(-1, Math.min(1, forwardValue + rightValue));
        double rightDriveSpeed = Math.max(-1, Math.min(1, forwardValue - rightValue));
        
        CommandBase.driveSubsystem.setSpeeds(rightDriveSpeed, leftDriveSpeed);
        
        // Tilt/Pan
        double tiltValue = OI.leftDrivejoystick.getY();
        double rotateValue = OI.leftDrivejoystick.getX();
        
        if (Math.abs(tiltValue) > 0.2) {
            CommandBase.shooterSubsystem.startTilt(tiltValue);
        } else {
            CommandBase.shooterSubsystem.stopTilt();
        }
        
        if (Math.abs(rotateValue) > 0.2) {
            CommandBase.shooterSubsystem.startPan(rotateValue);
        } else {
            CommandBase.shooterSubsystem.stopPan();
        }
        
        // Buttons
        if (OI.fireButton.get()){ 
            //when you fire the button, these commands should be executed
            CommandGroup group = new CommandGroup();
            group.addSequential(new OptimizeTrajectory());
            group.addSequential(new Shoot());
            
            group.start();
        }
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        
    }
}
