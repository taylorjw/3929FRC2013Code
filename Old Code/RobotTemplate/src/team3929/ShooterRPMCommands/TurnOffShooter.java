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
public class TurnOffShooter extends CommandBase {

    
    public TurnOffShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.rpmSpeedController.disable();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //rotate shooter based on the axes of the attack3 controller

        shooter.rotateTurretByJoy((-oi.getAttackX())*.4);

        //shooter.spinUpToPowerLevel(-oi.getAttackY());


        //shooter.spinUpToPowerLevel(-oi.getAttackY());
        
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
