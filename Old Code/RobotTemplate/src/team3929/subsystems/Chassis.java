/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3929.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import team3929.commands.DriveCommands.DriveWithJoystick;
import team3929.subsystems.DriveTrain;
import team3929.templates.RobotMap;
import team3929.utilities.ChassisSpeedController;
import team3929.utilities.PIDDrive;

/**
 *
 * @author Carter
 * @version 2012-03-02-1756 bhgray
 */
public class Chassis extends Subsystem {

    PIDDrive drive;
    //Encoder leftEncoder;
    //Encoder rightEncoder;
    boolean isLocked;
    public Encoder rightDriveEncoder;
     public Encoder leftDriveEncoder;
    public PIDController DriveSpeedController;
     public Chassis() {
        isLocked = false;
        drive = new PIDDrive(RobotMap.DPWM_driveJag1, RobotMap.DPWM_driveJag2, RobotMap.DPWM_driveJag3, RobotMap.DPWM_driveJag4);//instantiate RobotDrive on ports 1,2,3,4 for left and right motors

        //leftEncoder = new Encoder(RobotMap.DIO_driveEncoder1Channel1, RobotMap.DIO_driveEncoder1Channel2, false);
        rightDriveEncoder = new Encoder(8, 7, false);
        rightDriveEncoder.setDistancePerPulse((6.0 * Math.PI)/250.0);
        rightDriveEncoder.start();
        leftDriveEncoder = new Encoder(6, 5, false);
        leftDriveEncoder.setDistancePerPulse((6.0 * Math.PI)/250.0);
        leftDriveEncoder.start();
        rightDriveEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        DriveSpeedController = new ChassisSpeedController(rightDriveEncoder, drive);
        DriveSpeedController.enable();
        
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick()); // set default command
    }

    public void straight() { // sets the motor speeds to drive straight (no turn)
        if (!isLocked) {
            drive.tankDrive(0.25, 0.25);
        }
    }
    
    public double getRightEncoderSpeed(){
        double rate = rightDriveEncoder.getRate();
        return rate;
    }
    public double getLeftEncoderSpeed(){
        double rate = leftDriveEncoder.getRate();
        return rate;
    }
    public void goBackwards(){
        
            drive.tankDrive(-0.8,-0.8); 
        
    }

    public void turnLeft() { // sets the motor speeds to start a left turn
        if (!isLocked) {
            drive.tankDrive(0.0, .45);
        }
    }

    public void unlock() {
        isLocked = false;
    }

    public void driveWithJoystick(double stickLeft, double stickRight) {
        if (!isLocked) {
            drive.tankDrive(stickRight, stickLeft);
        }
    }

    public void lock() {
        isLocked = true;
        drive.tankDrive(0, 0);
    }

    public void arcadeDrive(double JoyLeftY, double JoyLeftX) {
        drive.arcadeDrive(JoyLeftY,JoyLeftX);
    }

    public void stop() {
        drive.tankDrive(0, 0);
    }

}
