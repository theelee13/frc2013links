
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

public class DriveSubsystem extends Subsystem {

    private Victor leftDrive = new Victor(RobotMap.leftDriveChannel);
    private Victor rightDrive = new Victor(RobotMap.rightDriveChannel);
    
    public void initDefaultCommand() {
    }
    
    public void setSpeeds(double rightSpeed, double leftSpeed){
        leftDrive.set(-leftSpeed);
        rightDrive.set(rightSpeed);
    }
    
    public void stop(){
        leftDrive.set(0);
        rightDrive.set(0);
    }
    
}

