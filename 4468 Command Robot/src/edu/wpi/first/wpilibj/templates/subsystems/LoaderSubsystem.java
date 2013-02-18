
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar; 
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap; 

public class LoaderSubsystem extends Subsystem {

    //declare motors necessary 
    private PWM loaderMotor = new PWM(RobotMap.loaderMotorChannel); 
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void loadFrisbee(){ //moves the motor for x amount of time depending on the value of degrees 
        loaderMotor.setRaw(255);  
    }
	
    public void reset(){ //resets the arm back into position
        loaderMotor.setRaw(1);
        Timer.delay(2); 
        loaderMotor.setRaw(0); 
    }
}
