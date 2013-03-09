/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.command.Subsystem; 
import edu.wpi.first.wpilibj.templates.RobotMap;

public class AccelerometerSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Accelerometer accelerometer = new Accelerometer(RobotMap.accelerometerPort);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getAcceleration(){
        return accelerometer.getAcceleration();
    }
}
