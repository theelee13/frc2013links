/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author s8222247
 */
public class AutonomousCommand extends CommandBase {
    
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.cameraSubsystem);
        requires(CommandBase.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
             try{
    CommandBase.cameraSubsystem.takeImage();
    ColorImage image = CommandBase.cameraSubsystem.getImage();
    CriteriaCollection cc = new CriteriaCollection();
    cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA,10,60,false);
    BinaryImage bImage = image.thresholdRGB(252, 255, 252, 255, 252, 255);
    BinaryImage bigImage = bImage.removeLargeObjects(false , 3); 
    BinaryImage conImage = bigImage.convexHull(false); 
    BinaryImage filter = conImage.particleFilter(cc);
    ParticleAnalysisReport[] reports = filter.getOrderedParticleAnalysisReports();
    for(int i =0;i<reports.length;i++){
        System.out.println(reports[i].center_mass_x);
        if(reports[i].center_mass_x<300){
            CommandBase.driveSubsystem.setSpeeds(-.21, .21);
        } else if(reports[i].center_mass_x>340){
            CommandBase.driveSubsystem.setSpeeds(.21, -.21);
        } else {
            CommandBase.driveSubsystem.stop();
        }
    }
    bImage.free();
    bigImage.free();
    conImage.free();
    filter.free();
    CommandBase.cameraSubsystem.freeCurrentImage();
    } catch (Exception e){ 
    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
