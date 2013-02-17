
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar; 
import edu.wpi.first.wpilibj.Timer; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap; 

public class LoaderSubsystem extends Subsystem {

    //declare motors necessary 
    private Jaguar loaderMotor = new Jaguar(RobotMap.loaderMotorChannel); 
    private static final int MAXRPM = 5000; 
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void loadFrisbee(double degrees){ //moves the motor for x amount of time depending on the value of degrees
        double secondsToMove = degrees/(MAXRPM*60); 
        loaderMotor.set(1); 
        Timer.delay(secondsToMove); 
        loaderMotor.set(0); 
    }
	
    public void reset(double degrees){ //resets the arm back into position
        double secondsToMove = degrees/(MAXRPM*60);
        loaderMotor.set(-1); 
        Timer.delay(secondsToMove); 
        loaderMotor.set(0); 
    }
    
}
