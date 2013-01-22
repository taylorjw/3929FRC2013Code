/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.ShooterRPMCommands;

import edu.wpi.first.wpilibj.Timer;
import team3929.commands.CommandBase;
import team3929.commands.CommandBase;
import team3929.subsystems.Vision;

/**
 *
 * @author bhgray
 */
public class ShootingAuton extends CommandBase {

    
    public static final boolean debug = true;
    public static final double setpoint =480;

    public ShootingAuton() {
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        requires(ballIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      
        shooter.spinUpToRPM(setpoint);
            Timer.delay(5);
            ballIntake.turnOnVerticalConveyor();
            Timer.delay(3);
            ballIntake.reverseHorizontalConveyor();
            Timer.delay(5);
            ballIntake.turnOffHorizontalConveyor();
            ballIntake.turnOffVerticalConveyor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        
        //armed = false;
        //isSpinning = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
    }
}
