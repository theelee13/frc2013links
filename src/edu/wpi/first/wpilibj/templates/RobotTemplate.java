/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class RobotTemplate extends SimpleRobot {
    RobotDrive drive;
    Joystick left, right;
    LiveWindow lw = new LiveWindow();

    public RobotTemplate(){
        getWatchdog().setEnabled(false);
        drive=new RobotDrive(1, 2);
        left = new Joystick(1);
        left.setAxisChannel(Joystick.AxisType.kY, 1);
        left.setAxisChannel(Joystick.AxisType.kX, 2);
    }
    public void autonomous() {

    for (int i = 0; i < 4; i++)
        {
           drive.drive(0.5, 0.0); // drive 50% fwd 0% turn
            Timer.delay(2.0);    
            drive.drive(0.0, 0.75); // drive 0% fwd, 75% turn
        }
    drive.drive(0.0, 0.0); 
    }

    public void operatorControl() {
        while(true && isOperatorControl() && isEnabled()){
            drive.arcadeDrive(left);
            Timer.delay(.005);
        }
    }
}
