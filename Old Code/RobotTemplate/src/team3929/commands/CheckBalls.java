/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team3929.commands.CommandBase;
import team3929.templates.RobotMap;

/**
 *
 * @author Carter
 */
public class CheckBalls extends CommandBase {

    boolean inConveyorMode = false;

    public CheckBalls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(ballIntake); //Requires the ballIntake Subsystem
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!inConveyorMode) {
            if (oi.checkAttackRealButton(Joystick.ButtonType.kTrigger)) { //Method from the oi class that checks if the button is pressed and if it is pressed
                ballIntake.turnOnVerticalConveyor();//then uses the turnOnConveyorBelts method from ballIntake

            } else if (!oi.checkAttackButton(6) && !oi.checkAttackRealButton(Joystick.ButtonType.kTrigger)) {
                ballIntake.turnOffVerticalConveyor();
            } else if (oi.checkAttackButton(6)) {
                ballIntake.outVerticalConveyor();
            }
            if (oi.checkAttackButton(5)) { //Method from the oi class that checks if the button is pressed and if it is pressed
                ballIntake.reverseHorizontalConveyor();//then turn off the conveyor belts
            } else if (!oi.checkAttackButton(4) && !oi.checkAttackButton(5)) { //Method from the oi class that checks if the button is pressed and if it is pressed
                ballIntake.turnOffHorizontalConveyor();//then turn off the conveyor belts

            } else if (oi.checkAttackButton(4)) {
                ballIntake.turnOnHorizontalConveyor();
            }
        } else {
            
                ballIntake.determineConveyorActivity();
            
        }
        if (oi.checkAttackButton(7)) {
            ballIntake.allocateSensorActivity();
        }
        if (oi.checkAttackButton(10)) {
            if (inConveyorMode) {
                inConveyorMode = false;
            } else {
                inConveyorMode = true;
            }
        }
        if(ballIntake.checkBSensor() && ballIntake.checkMSensor() && ballIntake.checkTSensor()){
            
                inConveyorMode = false;
            
        }

        SmartDashboard.putBoolean("Conveyor Mode", inConveyorMode);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//Never returns true
    }

    // Called once after isFinished returns true 
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
