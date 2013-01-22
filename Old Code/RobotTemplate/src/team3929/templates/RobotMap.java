package team3929.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    /** For example to map the left and right motors, you could define the
     following variables to use with your drivetrain subsystem.
     public static final int leftMotor = 1;
     public static final int rightMotor = 2;
    
     If you are using multiple modules, make sure to define both the port
     number and the module. For example you with a rangefinder:
     public static final int rangefinderPort = 1;
     public static final int rangefinderModule = 1;
    */
         

    


    //Digital PWM has 4 Jaguars devoted to drive, 2 Jaguars devoted to shooting,
    //and 2 Victors devoted to Ball Intake

    public static final int DPWM_driveJag1 = 1;
    public static final int DPWM_driveJag2 = 2;
    public static final int DPWM_driveJag3 = 3;
    public static final int DPWM_driveJag4 = 4;

   public static final int DPWM_ballInVictor1 = 5;
    public static final int DPWM_ballInVictor2 = 6;
    public static final int DPWM_armVictor = 10;
    public static final int DPWM_shooterJag3 = 9;
    //this is actually two jaguars hooked up iwth a y-pwm^
//    public static final int DPWM_shooterJag4 = 10;
    public static final int DPWM_TurretVic1 = 7;
    //this is the victor controlling the turret
//    public static final int DPWM_shooterVic2 = 8;
    

    //Digital relays have 1 Spike devoted to the arm, and 2 Spikes devoted to
    //the shooter
    public static final int DIO_shooterEncoderChannel1 = 10;
    public static final int DIO_shooterEncoderChannel2 = 11;
    //not using second channel because we don't care if the shooter wheels are going backwards
    public static final int MC_A = 1;
    public static final int MC_B = 2;
    public static final int MC_Y = 4;
    public static final int MC_X = 3;
    public static final int MC_LB = 5;
    public static final int MC_RB = 6;
    public static final int MC_leftJoy = 7;
    public static final int MC_rightJoy = 8;
    public static final int MC_select = 9;
    public static final int MC_start = 10;

    //Vision Stuff
    public static final double areaThresh = 444;
    public static final double rectThresh = 70;
    public static final double aspectThresh = 0;
    public static final int cameraHeight = 240;
    public static final int cameraWidth = 320;
    public static final double cameraOnTurretHeight = 3.25;

    public static final int lowHThresh = 0;
    public static final int highHThresh = 255;
    public static final int lowSThresh = 100;
    public static final int highSThresh = 255;
    public static final int lowLThresh = 135;
    public static final int highLThresh = 255;

    public static String TEAM_PREFIX_VISION = "[TEAM 3929 VISION] ";

}
