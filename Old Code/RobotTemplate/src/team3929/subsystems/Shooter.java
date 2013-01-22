/*
 * The class represents the shooter, hood and the turret subsystems.
 */
package team3929.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import team3929.commands.ManualShooterControl;
import team3929.templates.RobotMap;
import team3929.utilities.MovingAverageFilter;
import team3929.utilities.ShooterSpeedController;

/**
 *
 * @author Carter Henderson
 * @author Brent Gray
 * @version 2012-03-03-1308
 */
public class Shooter extends Subsystem {

    private static final double SHOOTER_MOTOR_STARTUP_POWER = 0.2;
    private static final double SHOOTER_MOTOR_SPINDOWN_POWER = 0.0;
    private boolean atSpeed = false;
    private boolean atAngle = false;
    /***********************  SHOOTER SUBSYSTEM *********************** */
    // the shooter has two Jags being controlled off one PWM
    // therefore this Jaguar acutally represents both
    private Jaguar shooterMotors;
    public Counter rpmEncoder;
    public PIDController rpmSpeedController;
    private MovingAverageFilter rpmFilter;
    public static final int kCountsPerRev = 2;
    /***********************  HOOD SUBSYSTEM *********************** */
    /***********************  TURRET SUBSYSTEM *********************** */
//    private AnalogChannel turretRotationPot;
    public Victor turretRotationMotor;
    private PIDController turretRotationController;
    private static final double maxRPM = 3000.0;
    //filter variables
    private static final double Alpha = 0.8;
    private static double filteredRPM = 0.0;

    public Shooter() {
        shooterMotors = new Jaguar(RobotMap.DPWM_shooterJag3);

        // at k4X, 1440 cycles per revolution (360 pulses per revolution)  this allows for a max of 10,000 rpm
        // see specs for details
        // ChiefDelphi advises to use 1x encoder type to minimize phase errors.
        // we are not using the Encoder class -- just a counter b/c we don't care about the phase differences

        // now using just a simple counter.
        rpmEncoder = new Counter(RobotMap.DIO_shooterEncoderChannel1);
        rpmEncoder.start();
        rpmSpeedController = new ShooterSpeedController(this, shooterMotors);
        rpmSpeedController.enable();


        // this PIDController will need to be tuned
        // the PIDSource is the vision system (the azimuth)
        // the PIDOutput is the motor.

        turretRotationMotor = new Victor(RobotMap.DPWM_TurretVic1);
        //turretRotationController = new PIDController(0.1, 0.001, 0.0, Vision.getInstance(), turretRotationMotor);
        //turretRotationController.enable();
    }

    //@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualShooterControl());
    }

    /*********************** TURRET SUBSYSTEM METHODS *********************** */
    // mechanical angle on the pot is 0 (90 degrees LEFT) to 180 (90 degrees RIGHT)
    // input is in the drivetrain coordinate system (0 is straight ahead)

    /* IF there is a pot on the turrent, we can go directly to an angle by setting
     * the setpoint to an explicit angle.
     * ELSE we are simply comparing the current turrent angle against the aiming
     * line in the ByrdsEye (i.e., the azimuth reading) and trying to get the
     * difference to 0.
     */
    /*
    public void rotateTurretToAngle(int angle) {
    // turretRotationController.setSetpoint(convertDriverAngleToPotAngle(angle));
    turretRotationController.setSetpoint(angle);
    }

    public void alignTurretWithTargetingAzimuth()
    {
    atAngle = false;
    turretRotationController.setSetpoint(0);
    atAngle = true;
    System.out.println("On target angle.");
    }
     */
    public void rotateTurretByJoy(double speed) {
        turretRotationMotor.set(speed);
    }

    // Joystick is [-1..1]
    // TODO:  calibrate turret to joystick on manual
    /*
    public void rotateTurretToAngleByJoystick(double d) {
    this.rotateTurretToAngle((int) (d + 1 * 2.5));
    }
     * */
    /*********************** SHOOTER SUBSYSTEM METHODS *********************** */
    // spins up the shooter to a present minimum level

    
    
    public double getShooterSpeedRPM() {
        double period = rpmEncoder.getPeriod();
        
        if (period == 0.0) {
            return 0.0;
        } else {
            //return ((60.0/rpmEncoder.getPeriod())/250.0);
            return ((0.24) / (rpmEncoder.getPeriod()));
        }
    }
    
    public double getShooterSpeedRPMFiltered(){
        filteredRPM = (1-Alpha)*(getShooterSpeedRPM() + (Alpha * filteredRPM));
        return filteredRPM;
    }

    /*
     * this is raw power level.  useful only in the joystick (manual)
     * context
     */
    public void spinUpToPowerLevel(double power) {
        shooterMotors.set(power);
    }

    public void spinUpToRPM(double rpms) {
        
        rpmSpeedController.setSetpoint(rpms);

        System.out.println("Setpoint: " + (this.rpmSpeedController.getSetpoint()));
        System.out.println("At speed: " + this.getShooterSpeedRPMFiltered());
    }

   

    public boolean onTarget() {
        return rpmSpeedController.onTarget();
    }

    /*********************** HOOD SUBSYSTEM METHODS *********************** */
    public PIDSource getRPMPIDSource() {
        return new RPMPIDSource();
    }

    public PIDOutput getRPMPIDOutput() {
        return new RPMPIDOutput();
    }

    private class RPMPIDSource implements PIDSource {

        //@Override
        public double pidGet() {
            return (getShooterSpeedRPMFiltered());
        }
    }

    private class RPMPIDOutput implements PIDOutput {

        //@Override
        public void pidWrite(double output) {
            //  throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
