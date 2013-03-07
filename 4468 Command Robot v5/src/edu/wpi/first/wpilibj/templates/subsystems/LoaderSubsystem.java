
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar; 
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap; 

public class LoaderSubsystem extends Subsystem {

    //declare motors necessary 
    private Relay loaderMotor = new Relay(RobotMap.loaderMotorChannel); 
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void loadFrisbee(){ //moves the motor for x amount of time depending on the value of degrees 
        loaderMotor.setDirection(Relay.Direction.kForward); 
        Timer.delay(2); 
        loaderMotor.set(Relay.Value.kOn); 
    }
	
    public void reset(){ //resets the arm back into position
        loaderMotor.setDirection(Relay.Direction.kReverse);
        Timer.delay(2); 
        loaderMotor.set(Relay.Value.kOff); 
    }
}
