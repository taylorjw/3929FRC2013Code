/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team3929.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Scheduler;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Darwin extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    Talon talon1;
    Talon talon2;
    Talon talon3;
    Talon talon4;
    Joystick joy1 = new Joystick(1);
    Joystick joy2 = new Joystick(2);
    public void robotInit() {
             talon1 = new Talon(1);
             talon2 = new Talon(2);
             talon3 = new Talon(3);
             talon4 = new Talon(4);
            
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
            talon1.set(.5);
            
            Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        double Yjoy1 = joy1.getY();
        double Yjoy2 = joy2.getY();
        
        talon1.set(Yjoy1);
        talon2.set(Yjoy1);
        talon3.set(Yjoy2);
        talon4.set(Yjoy2);
        Scheduler.getInstance().run();
    }
    
}
