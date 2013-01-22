/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team3929.commands.CommandBase;
import team3929.subsystems.Vision;

/**
 *
 * @author bhgray
 */
public class ShooterAutonDriveArm extends CommandBase {


    public static final boolean debug = true;
    public static final double setpoint =490;
    public int numExecutes = 0;
    public ShooterAutonDriveArm() {
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        requires(ballIntake);
        requires(chassis);
        requires(armControl);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    chassis.DriveSpeedController.enable();
    chassis.rightDriveEncoder.reset();
        /*shooter.spinUpToRPM(setpoint);
            Timer.delay(5);
            ballIntake.reverseHorizontalConveyor();
            ballIntake.turnOnVerticalConveyor();
            Timer.delay(2);
            ballIntake.turnOffHorizontalConveyor();
            ballIntake.turnOffVerticalConveyor();
            Timer.delay(2);
            ballIntake.reverseHorizontalConveyor();
            ballIntake.turnOnVerticalConveyor();
            Timer.delay(2);
            ballIntake.turnOffHorizontalConveyor();
            ballIntake.turnOffVerticalConveyor();
         * 
         */
            
            //Timer.delay(3);
            //chassis.stop();
            
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        chassis.DriveSpeedController.setSetpoint(40);
        SmartDashboard.putDouble("Right Drive Encoder: ",chassis.rightDriveEncoder.getDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return chassis.DriveSpeedController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    chassis.stop();
    chassis.DriveSpeedController.disable();
        //armed = false;
        //isSpinning = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        chassis.stop();
        chassis.DriveSpeedController.disable();
    }
}
