/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import team3929.commands.DriveCommands.TankDriveLogitech;
import team3929.templates.RobotMap;

/**
 *
 * @author PROGRAMING FRC 2013
 */
public class Chassis extends Subsystem {
    RobotDrive drive;
    public Chassis() {
        
        drive = new RobotDrive(RobotMap.TalonOnePort, RobotMap.TalonTwoPort, RobotMap.TalonThreePort, RobotMap.TalonFourPort);//instantiate RobotDrive on ports 1,2,3,4 for left and right motors

        
        
    }
    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveLogitech()); // set default command
    }

    public void straight() { // sets the motor speeds to drive straight (no turn)
        
            drive.tankDrive(0.25, 0.25);
        
    }
    
    
    public void goBackwards(){
        
            drive.tankDrive(-0.8,-0.8); 
        
    }

    public void turnLeft() { // sets the motor speeds to start a left turn
       
            drive.tankDrive(0.0, .45);
        
    }

    

    public void driveWithJoystick(double stickLeft, double stickRight) {
        
            drive.tankDrive(stickLeft, stickRight);
        
    }

    

    public void arcadeDrive(double JoyLeftY, double JoyLeftX) {
        drive.arcadeDrive(JoyLeftY,JoyLeftX);
    }

    public void stop() {
        drive.tankDrive(0, 0);
    }
}
