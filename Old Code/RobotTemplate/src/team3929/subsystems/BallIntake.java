/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import team3929.commands.CheckBalls;
import team3929.templates.RobotMap;

/**
 *
 * @author Carter
 */
public class BallIntake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Victor verticalConveyor;
    Victor backConveyor;
    DigitalInput bSensor;
    DigitalInput mSensor;
    DigitalInput tSensor;
    boolean ballInBottom;
    boolean ballInMiddle;
    boolean ballInTop;
    public boolean isInAutoIntake = false;
    public int ballCounter; //New int ballCounter counts for the number of balls in the robot

    public BallIntake() {
        verticalConveyor = new Victor(RobotMap.DPWM_ballInVictor1); //New victor for the front of the conveyor belt and sets the Victor the corresponding port on RobotMap
        backConveyor = new Victor(RobotMap.DPWM_ballInVictor2); //New victor for the back of the Conveyor belt
        bSensor = new DigitalInput(11);
        mSensor = new DigitalInput(12);
        tSensor = new DigitalInput(13);

    }

    public void initDefaultCommand() {
        // Set the default command to CheckBalls
        setDefaultCommand(new CheckBalls());
    }

    public void turnOnHorizontalConveyor() { //New method that sets both conveyor belts on and at the same speed
        backConveyor.set(.8);
    }

    public void turnOnVerticalConveyor() {
        verticalConveyor.set(.4);
    }

    public void outVerticalConveyor() {
        verticalConveyor.set(-.4);
    }

    public void turnOffVerticalConveyor() {
        verticalConveyor.set(0);
    }

    public void reverseHorizontalConveyor() { //New method that turns off both conveyor belts
        backConveyor.set(-.6);
    }

    public void turnOffHorizontalConveyor() {
        backConveyor.set(0.0);
    }

    public boolean checkBSensor() {

        return bSensor.get();
    }

    public boolean checkMSensor() {
        return mSensor.get();
    }

    public boolean checkTSensor() {
        return tSensor.get();
    }

    public void allocateSensorActivity() {
        ballInBottom = checkBSensor();
        ballInMiddle = checkMSensor();
        ballInTop = checkTSensor();
        System.out.println("Bottom: " + checkBSensor());
        System.out.println("Middle: " + checkMSensor());
        System.out.println("Top: " + checkTSensor());
    }

    public void determineConveyorActivity() {

        if (checkBSensor() && !checkMSensor()&&!checkTSensor()) {
                turnOnVerticalConveyor();
        }
         else if(!checkBSensor() && !checkMSensor() && !checkTSensor()){
         turnOnVerticalConveyor();
         reverseHorizontalConveyor();
         
        }
        else if(!checkBSensor() && checkMSensor()&&!checkTSensor()){
            turnOffVerticalConveyor();
            
        }
        else if(checkBSensor() && checkMSensor()&&!checkTSensor())
        {
            turnOnVerticalConveyor();
        }
        else if(checkBSensor() &&checkMSensor() &&checkTSensor())
        {
            turnOffVerticalConveyor();
            turnOffHorizontalConveyor();
            
        }
        else if(!checkBSensor() &&checkMSensor() &&checkTSensor())
        {
            turnOffVerticalConveyor();
            
        }
        else if(checkBSensor() &&!checkMSensor() &&checkTSensor())
        {
            turnOffVerticalConveyor();
            
        }
        else if(!checkBSensor() &&!checkMSensor() &&checkTSensor())
        {
            turnOffVerticalConveyor();
            
        }
        }
    
}
