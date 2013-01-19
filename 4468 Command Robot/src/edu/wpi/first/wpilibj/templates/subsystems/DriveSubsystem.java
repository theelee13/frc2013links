
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Jaguar leftDrive = new Jaguar(RobotMap.leftDrive);
    Jaguar rightDrive = new Jaguar(RobotMap.rightDrive);
    
    public void initDefaultCommand() {
    }
    
    public void setSpeeds(double rightSpeed, double leftSpeed){
        leftDrive.set(leftSpeed);
        rightDrive.set(rightSpeed);
    }
    
    public void stop(){
        leftDrive.set(0);
        rightDrive.set(0);
    }
    
}

