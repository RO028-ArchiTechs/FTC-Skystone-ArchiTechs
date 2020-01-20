package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="~v1.0-left", group="Linear Opmode")
//@Disabled
public class AOnePointOhL extends LinearOpMode {

    // Declare Hardware, timing, etc
    private ElapsedTime runtime = new ElapsedTime();
    HardwareDrivetrain drive = new HardwareDrivetrain();
    private Servo  mechGrab = null;


    /*
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;
    static final double     WHEEL_DIAMETER_CM       = 4.0 ;
    static final double     COUNTS_PER_CM           = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_CM * Math.PI);

     */
    @Override
    public void runOpMode() throws InterruptedException
    {

        //Setup Hardware
        drive.init(hardwareMap);
        mechGrab = hardwareMap.get(Servo.class, "GR");


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //INPUT
        double vX=0, vY=0, vR=0;

        double LocalX = 0, LocalY = 0; //robot speed in local coordinates
        double X=0, Y=0, A=0;
        //OUTPUT
        double  power_FL, power_FR,

                power_RL, power_RR;




        // run until the end of the match (driver presses STOP)
        mechGrab.setPosition(1.0);
        Drive(-.7,0,0,900);
        Stop();
        Drive(0,.5,0,2500);
        Stop();







        // Send calculated power to wheels


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        telemetry.addData("IN", "Y (%.2f), X (%.2f)", vY, vX);

        //telemetry.addData("Motors", "FL (%.2f), FR (%.2f), RL (%.2f), RR (%.2f)", power_FL, power_FR, power_RL, power_RR);

        telemetry.update();


    }

    public void Drive(double X,double Y,double R, long time) throws InterruptedException
    {
        drive.FL.setPower ( X +Y -R);
        drive.FR.setPower (-X +Y +R);
        drive.RL.setPower (-X +Y -R);
        drive.RR.setPower ( X +Y +R);
        Thread.sleep(time);
    }



    public void Stop() throws InterruptedException
    {
        Drive (0,0,0,0);
    }
}