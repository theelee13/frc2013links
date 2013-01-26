
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage; 
import edu.wpi.first.wpilibj.image.BinaryImage; 
import edu.wpi.first.wpilibj.image.NIVisionException;


public class CameraSubsystem extends Subsystem {
    private ColorImage currentImage; 
    private AxisCamera camera = AxisCamera.getInstance();
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void takeImage(){ 
        try {
            currentImage = camera.getImage();
        } catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getImageHeight(){
        int temp = 0; 
        try { 
            temp = currentImage.getHeight();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        finally{
            return temp; 
        }
    }
    
    public int getImageWidth(){
        int temp = 0; 
        try { 
            temp =  currentImage.getWidth();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        finally{
            return temp; 
        }
    }
    
    public void freeCurrentImage(){
        try {
            currentImage.free();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
    }
        
    public ColorImage getImage(){ 
        return currentImage;
    }


}
