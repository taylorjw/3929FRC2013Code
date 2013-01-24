/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;


/**
 *
 * @author taylorwashington
 */
public class DiscIntake {
    
    public boolean isInAutoIntake = false;
    public Victor rollerMotor;
    DigitalInput aSensor;
    
    public DiscIntake(){
        aSensor = new DigitalInput(1); // change this later      
    }
    
    public void turnOnRoller(){
        rollerMotor.set(1); // change this later
    }
    
    public void turnOffRoller(){
        rollerMotor.set(0.0); // change this later
    }
    
    public void reverseRoller(){
        rollerMotor.set(-1); // change this later
    }
    
}
