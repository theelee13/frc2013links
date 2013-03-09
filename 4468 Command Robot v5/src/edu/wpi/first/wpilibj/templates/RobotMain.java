 /*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer; 
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

//    Command autonomousCommand;
    public double currentShooterSpeed = 1.0; 
    public boolean isDriveAllowed = true; 
    public boolean isAutonomousAllowed = true; 
    public boolean isShooterMotorAllowed = true;
    public boolean isPanningAllowed = true;
    public boolean isTiltingAllowed = true; 
    public boolean isAllowedToFire = true; 
    public AutonomousCommand autonomousCommand; 
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //Initialize all subsystems
        CommandBase.init();
        
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand(0,0); 
    }

    public void autonomousInit() {
        // schedule the autonomous command
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        autonomousCommand.cancel();
      
        OI.leftDrivejoystick.setAxisChannel(Joystick.AxisType.kX, RobotMap.rightDriveChannel);
        OI.leftDrivejoystick.setAxisChannel(Joystick.AxisType.kY, RobotMap.leftDriveChannel);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() { //manual control and autonomous image processing added
        Scheduler.getInstance().run();
        
        if(OI.emergencyStopFiringButton.get()){
            isAllowedToFire = !(isAllowedToFire); 
        }
        if(OI.emergencyBreakDriveButton.get()){
            isDriveAllowed = !(isDriveAllowed); 
            CommandBase.driveSubsystem.setSpeeds(0.0, 0.0);
        }
        if(OI.emergencyShooterStopButton.get()){
            isShooterMotorAllowed = !(isShooterMotorAllowed);
            CommandBase.shooterSubsystem.setShooterSpeed(0.0);
        }
        if(OI.emergencyEndAutonmousTrackingCodeButton.get()){
            isAutonomousAllowed = !(isAutonomousAllowed); 
        }

        if(OI.emergencyPanStopButton.get()){
            isPanningAllowed = !(isPanningAllowed); 
        }
        if(OI.emergencyTiltStopButton.get()){
            isTiltingAllowed = !(isTiltingAllowed); 
        }
        if(OI.getCurrentStatsButton.get()){
            System.out.println("Current shooter speed: " + currentShooterSpeed); 
            System.out.println("Is driving allowed" + isDriveAllowed); 
            System.out.println("Is Autonomous Allowed?: " + isAutonomousAllowed);
            System.out.println("Is shooter motor allowed?: " + isShooterMotorAllowed);
            System.out.println("Is tilting allowed?: " + isTiltingAllowed);
            System.out.println("Is panning allowed?: " + isPanningAllowed); 
        }
        
        // Driving
        double forwardValue = OI.leftDrivejoystick.getY();
        double rightValue = OI.leftDrivejoystick.getX(); 
        
        double leftDriveSpeed = Math.max(-1, Math.min(1, forwardValue + rightValue));
        double rightDriveSpeed = -Math.max(-1, Math.min(1, forwardValue - rightValue));
        
        if(isDriveAllowed){
            CommandBase.driveSubsystem.setSpeeds(rightDriveSpeed, leftDriveSpeed);
        }
       
        
        // Tilt/Pan
        double tiltValue = OI.shooterJoystick.getY();
        double rotateValue = OI.shooterJoystick.getX(); 
       
        if(Math.abs(tiltValue) > 0.2){
            if(isTiltingAllowed){
                CommandBase.shooterSubsystem.startTilt(tiltValue); 
                System.out.println("Tilting at " + tiltValue);
            }
        }
        else{
            CommandBase.shooterSubsystem.stopTilt();
        }
          
        if(Math.abs(rotateValue) > 0.2){
            if(isPanningAllowed){
                CommandBase.shooterSubsystem.startPan(rotateValue);
                System.out.println("Rotating at " + rotateValue);
            }

        }
        else{
            CommandBase.shooterSubsystem.stopPan();
        }
        
        //image processing 
        //takes care of getting the shooter turret centered with the goal
        //NOTE: need to figure out which shooter speed is good for certain distances
        //NOTE: need to figure out how to keep track of the number of frisbees
        if(isAutonomousAllowed){
            
        }
        
        
        //manually increasing/decreasing shooter speed
        if(OI.increaseShooterSpeedButton.get()){
            double temp = currentShooterSpeed + 0.2; 
            if(temp > 1.0 || temp < 0.0){
                throw new IllegalArgumentException("PwnSpeed must be in range [0,1]"); 
            }
            else{
                currentShooterSpeed = temp; 
            }
        }
        if(OI.decreaseShooterSpeedButton.get()){
            double temp = currentShooterSpeed - 0.2; 
            if(temp > 1.0 || temp < 0.0){
                throw new IllegalArgumentException("PwnSpeed must be in range [0,1]"); 
            }
            else{
                currentShooterSpeed -= 0.1; 
            }
        }
        
        //manually starting shooter motors
        if(OI.startShooterMotorButton.get()){ //starts up the motor
            if(isShooterMotorAllowed){
               CommandBase.shooterSubsystem.setShooterSpeed(currentShooterSpeed); 
            }
        }   
        else{
            CommandBase.shooterSubsystem.setShooterSpeed(0.0); 
        }
        
        //manually loading/firing the frisbee
        if(OI.loadButton.get()){ //shoots the frisbee
            if(isShooterMotorAllowed){
               CommandBase.loaderSubsystem.loadFrisbee();
               CommandBase.loaderSubsystem.reset();  
            }
            
        }

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() { //just for testing loading and shooting
        CommandGroup group = new CommandGroup(); 
        group.addSequential(new OptimizeTrajectory());
        if(OI.stopOptimizationButton.get()){
            group.cancel();
        }
        if(OI.startOptimizationButton.get()){
            group.start();
        }
        if(OI.loadButton.get()){
            CommandBase.loaderSubsystem.loadFrisbee();
            CommandBase.loaderSubsystem.reset(); 
        }
        if(OI.startShooterMotorButton.get()){
            CommandBase.shooterSubsystem.setShooterSpeed(1.0);
        }
        else{
            CommandBase.shooterSubsystem.setShooterSpeed(0.0);
        }
        
        double tiltValue = OI.shooterJoystick.getY();
        double rotateValue = OI.shooterJoystick.getX(); 
        
        if(Math.abs(tiltValue) > 0.2){
            CommandBase.shooterSubsystem.startTilt(tiltValue); 
            System.out.println("Tilting at " + tiltValue);
        }
        else{
            CommandBase.shooterSubsystem.stopTilt();
        }
          
        if(Math.abs(rotateValue) > 0.2){
            CommandBase.shooterSubsystem.startPan(rotateValue);
            System.out.println("Rotating at " + rotateValue);
        }
        else{
            CommandBase.shooterSubsystem.stopPan();
        } 
        
    }
}
