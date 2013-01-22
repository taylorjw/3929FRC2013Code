/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package team3929.utilities;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Carter
 */
public class PIDDrive implements PIDOutput{
    protected SpeedController m_frontLeftMotor;
    protected SpeedController m_frontRightMotor;
    protected SpeedController m_rearLeftMotor;
    protected SpeedController m_rearRightMotor;
    RobotDrive drive;
    public PIDDrive(final int frontLeftMotor, final int rearLeftMotor, final int frontRightMotor, final int rearRightMotor){
        drive = new RobotDrive(frontLeftMotor,rearLeftMotor,frontRightMotor,rearRightMotor);
    }
    public void tankDrive(double stickRight, double stickLeft){
        drive.tankDrive(stickRight,stickLeft);
    }
    public void arcadeDrive(double JoyLeftY, double JoyLeftX ){
        drive.arcadeDrive(JoyLeftY,JoyLeftX);
    }

    public void pidWrite(double output) {
        drive.drive(-output, 0);
    }
}
