/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.ShooterRPMCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team3929.commands.CommandBase;

/**
 *
 * @author Carter
 */
public class FullPower extends CommandBase {

    private static int setpoint = 1000;
    public FullPower() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    shooter.rpmSpeedController.enable();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //rotate shooter based on the axes of the attack3 controller

        shooter.rotateTurretByJoy((-oi.getAttackX())*.25);

        //shooter.spinUpToPowerLevel(-oi.getAttackY());

        if (oi.checkAttackButton(8)) {
            setpoint -= 5;
        } else if (oi.checkAttackButton(9)) {
            setpoint += 5;
        } else {
        }
        //shooter.spinUpToPowerLevel(-oi.getAttackY());
        shooter.spinUpToRPM(setpoint);
        SmartDashboard.putDouble("Current Setpoint: ", shooter.rpmSpeedController.getSetpoint());
        SmartDashboard.putDouble("Shooter RPMS: " , shooter.getShooterSpeedRPMFiltered());
        SmartDashboard.putDouble("Shooter Power" , -oi.getAttackY());

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
