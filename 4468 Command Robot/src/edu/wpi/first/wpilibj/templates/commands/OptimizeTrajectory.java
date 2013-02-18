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
    private int counter;
    
    public OptimizeTrajectory() {
        requires(CommandBase.cameraSubsystem); 
        requires(CommandBase.shooterSubsystem);
        requires(CommandBase.loaderSubsystem);
        requires(CommandBase.driveSubsystem); 
    }

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
            System.out.println("Center was null - stopping execute()");
            onTarget = true;
            shooterSubsystem.stopPan();
            shooterSubsystem.stopTilt();
            return;
        }
        
        int width = 640;
        int height = 320;
        
        try {
            if(img != null){
                System.out.println("Image is not null - setting width and height");
                width = img.getWidth();
                height = img.getHeight();
            }
            if(center[0] > width/2 + 5){
                // pan right
                System.out.println("Panning right");
                shooterSubsystem.startPan(1);
            } else if (center[0] < width/2 - 5){
                // pan left
                System.out.println("Panning left");
                shooterSubsystem.startPan(-1);
            } else {
                System.out.println("On target - X. Stopping pan");
                onTargetX = true;
                shooterSubsystem.stopPan();
            }
            
            if(center[1] > height/2 + 5){
                // tilt down
                System.out.println("Tilting down");
                shooterSubsystem.startTilt(-1);
            } else if (center[1] < height/2 - 5){
                // tilt up
                System.out.println("Tilting up");
                shooterSubsystem.startTilt(1);
            } else {
                System.out.println("On target - Y. Stopping tilt");
                onTargetY = true;
                shooterSubsystem.stopTilt();
            }
            
        } catch (NIVisionException e) {
            System.err.println("Error processing image: " + e.getMessage());
            onTarget = true;
            return;
        }
        
        if(img != null){
            CommandBase.cameraSubsystem.freeCurrentImage();
        }
        
        onTarget = onTargetX && onTargetY;
    }
    
    
     protected int[] getCenterMass(ColorImage image){
        counter++;
        /*
        int[] temp = {10,10};
        if (counter % 10 != 0){
            System.out.println("Returning fake center");
            return temp;
        }
        */
        
	try{
            CriteriaCollection cc = new CriteriaCollection();
            cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA,10,60,false);
            
            BinaryImage bImage = image.thresholdRGB(230, 250, 230, 250, 0, 5); //change color sought
            BinaryImage bigImage = bImage.removeSmallObjects(false , 4); 
            BinaryImage conImage = bigImage.convexHull(false);
            BinaryImage filter = conImage.particleFilter(cc);
            ParticleAnalysisReport[] reports = filter.getOrderedParticleAnalysisReports();
            
            if (reports.length == 0){
                return null;
            }
            int[] rv = {reports[0].center_mass_x, reports[0].center_mass_y};
            
            bImage.free();
            bigImage.free();
            conImage.free();
            filter.free();
            if (counter % 10 != 0){
            System.out.println("Returning real center");
            return rv;
            }
        } catch (Exception e){  }
        
        return null;
        
        
        /*
         * every 9 counts, gets an image, and gets the x and y coordinate
         * of the first green value it sees. Then, it gets the
         * x and y of the green value, and returns that to be compared
         * to our pan and tilt location, when optimized, it will shoot.
         * This will be handled up there^
         */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("Found target");
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
