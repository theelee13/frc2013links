package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class OptimizeTrajectory extends CommandBase {
    private boolean onTarget = false;
    
    public OptimizeTrajectory() {
        requires(cameraSubsystem); 
        requires(shooterSubsystem);
    }

    private int counter;
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Initializing Trajectory Optimization...");
        counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean onTargetX = false, onTargetY = false;
        
        cameraSubsystem.takeImage(); 
        ColorImage img = cameraSubsystem.getImage();
        // center[0] = x, center[1] = y
        int[] center = this.getCenterMass(img);
        
        if(center == null) {
            onTarget = true;
            return;
        }
        
        try {
            if(center[0] > img.getWidth()/2 + 5){
                // pan right
                shooterSubsystem.startPan(1);
            } else if (center[0] < img.getWidth()/2 - 5){
                // pan left
                shooterSubsystem.startPan(-1);
            } else {
                onTargetX = true;
                shooterSubsystem.stopPan();
            }
            
            if(center[1] > img.getHeight()/2 + 5){
                // tilt down
                shooterSubsystem.startTilt(-1);
            } else if (center[1] < img.getHeight()/2 - 5){
                // tilt up
                shooterSubsystem.startTilt(1);
            } else {
                onTargetY = true;
                shooterSubsystem.stopTilt();
            }
            
        } catch (NIVisionException e) {
            System.err.println("Error processing image: " + e.getMessage());
            onTarget = true;
            return;
        }
        
        CommandBase.cameraSubsystem.freeCurrentImage();
        onTarget = onTargetX && onTargetY;
    }
    
    
    protected int[] getCenterMass(ColorImage image){
        int temp[] = {10,10};
        counter++;
        if (counter % 10 != 0)
            return temp;
        return null;
        /*
	try{
            CriteriaCollection cc = new CriteriaCollection();
            cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA,10,60,false);
            
            BinaryImage bImage = image.thresholdRGB(230, 250, 230, 250, 0, 5);
            BinaryImage bigImage = bImage.removeSmallObjects(false , 4); 
            BinaryImage conImage = bigImage.convexHull(false);
            BinaryImage filter = conImage.particleFilter(cc);
            ParticleAnalysisReport[] reports = filter.getOrderedParticleAnalysisReports();
            
            if (reports.length == 0)
                return null;
            
            int[] rv = {reports[0].center_mass_x, reports[0].center_mass_y};
            
            bImage.free();
            bigImage.free();
            conImage.free();
            filter.free();
            return rv;
        } catch (Exception e){  }
        
        return null;
         * 
         */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
