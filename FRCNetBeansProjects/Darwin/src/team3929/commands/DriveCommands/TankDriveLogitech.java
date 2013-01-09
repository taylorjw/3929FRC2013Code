/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.commands.DriveCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team3929.commands.CommandBase;
import team3929.templates.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author Carter
 */
public class TankDriveLogitech extends CommandBase {

    double JoyLeftY; //Declare Joysticks
    double JoyRightY;
    int reverser = 1; //This variable will allow for a button to control direction of motors
    public TankDriveLogitech() {
        requires(chassis); // reserve the chassis subsystem
    }
        protected void initialize() { // called once each time the command starts running
            
            //chassis.DriveSpeedController.disable();
        
     
        }
        protected void execute() { // called repeatedly while the command is running
            
            JoyLeftY = oi.getAttackY();
            JoyRightY = oi.getAttackSecondaryY();
            //JoyLeftX = -oi.getLeftX();
            
            
            //SmartDashboard.putDouble("Right Encoder Speed: ", chassis.getRightEncoderSpeed());
            //SmartDashboard.putDouble("Left Encoder Speed: ", chassis.getLeftEncoderSpeed());
                chassis.driveWithJoystick(JoyLeftY,JoyRightY);
 }
            
        protected boolean isFinished() { // called repeatedly and determines if the
            
            return false; // command is finished executing
    }
    // Called once after isFinished returns true
        protected void end() { // called after the command ends for clean up
    }
    protected void interrupted() { // called if the command is preempted or canceled
}
}
