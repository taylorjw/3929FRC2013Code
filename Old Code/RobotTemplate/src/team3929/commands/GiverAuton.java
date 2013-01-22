/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.commands;

import edu.wpi.first.wpilibj.Timer;
import team3929.commands.CommandBase;
import team3929.subsystems.Vision;


/**
 *
 * @author bhgray
 */
public class GiverAuton extends CommandBase {



    public synchronized void cancel() {
        super.cancel();
    }

    public GiverAuton() {

        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        requires(ballIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Timer.delay(7);
        ballIntake.turnOnHorizontalConveyor();
        ballIntake.verticalConveyor.set(-.3);
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
        
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
    }
}