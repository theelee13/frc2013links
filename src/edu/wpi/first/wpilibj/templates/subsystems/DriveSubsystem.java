
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

public class DriveSubsystem extends Subsystem {

    private Jaguar leftDrive = new Jaguar(RobotMap.leftDriveChannel);
    private Jaguar rightDrive = new Jaguar(RobotMap.rightDriveChannel);
    
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

