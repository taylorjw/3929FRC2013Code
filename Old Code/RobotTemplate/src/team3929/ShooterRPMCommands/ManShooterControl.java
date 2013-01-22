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
public class ManShooterControl extends CommandBase {

    double power;
    public static final int millisToMinutes = 300;
    public static final int numDegrees = 360;
    private static final double maxRPMs = 3000.0;
    private double setpoint = 250;

    public ManShooterControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        power = .1;
        shooter.rpmSpeedController.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //rotate shooter based on the axes of the attack3 controller

        shooter.rotateTurretByJoy((-oi.getAttackX()) * .25);

        //shooter.spinUpToPowerLevel(-oi.getAttackY());

        if (oi.checkAttackButton(11)) {
            shooter.rpmSpeedController.enable();
            shooter.rpmSpeedController.setSetpoint(setpoint);
        } else {
            shooter.rpmSpeedController.disable();
            shooter.spinUpToPowerLevel(-oi.getAttackY());
        }

        SmartDashboard.putDouble("Shooter RPMS: ", shooter.getShooterSpeedRPMFiltered());
        SmartDashboard.putDouble("Shooter Power", -oi.getAttackY());

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
