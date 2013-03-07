
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //Drive joystick
    public static final Joystick leftDrivejoystick = new Joystick(RobotMap.driveStickPort);
    public static final Button loadButton = new JoystickButton(leftDrivejoystick, RobotMap.loadButtonNumber);
    public static final Button emergencyBreakDriveButton = new JoystickButton(leftDrivejoystick, RobotMap.emergencyBreakButtonNumber); 
    public static final Button emergencyEndAutonmousTrackingCodeButton = new JoystickButton(leftDrivejoystick, RobotMap.emergencyEndAutonomousButtonNumber);
    public static final Button getCurrentStatsButton = new JoystickButton(leftDrivejoystick, RobotMap.getCurrentStatsButtonNumber); 
    public static final Button emergencyStopFiringButton = new JoystickButton(leftDrivejoystick, RobotMap.emergencyStopFiringButton);
    
    //Shooter joystick
    public static final Joystick shooterJoystick = new Joystick(RobotMap.shooterStickPort);
    public static final Button startShooterMotorButton = new JoystickButton(shooterJoystick,RobotMap.startShooterMotorButtonNumber);
    public static final Button increaseShooterSpeedButton = new JoystickButton(shooterJoystick, RobotMap.increaseShooterSpeedButtonNumber);
    public static final Button decreaseShooterSpeedButton = new JoystickButton(shooterJoystick, RobotMap.decreaseShooterSpeedButtonNumber);
    public static final Button emergencyShooterStopButton = new JoystickButton(shooterJoystick, RobotMap.emergencyShooterStopButtonNumber);  
    public static final Button emergencyTiltStopButton = new JoystickButton(shooterJoystick, RobotMap.emergencyStopTiltButton);
    public static final Button emergencyPanStopButton = new JoystickButton(shooterJoystick, RobotMap.emergencyStopPanButton);
    public static final Button startOptimizationButton = new JoystickButton(shooterJoystick, RobotMap.startOptimizationButtonNumber); 
    public static final Button stopOptimizationButton = new JoystickButton(shooterJoystick, RobotMap.stopOpTButton);
    
    
    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

