package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareDrivetrain;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Autonomous(name="T>Auto1", group="Linear Opmode")
//@Disabled
public class A1 extends LinearOpMode {

    // Declare Hardware, timing, etc
    private ElapsedTime runtime = new ElapsedTime();
    HardwareDrivetrain drive = new HardwareDrivetrain();
    private Servo mechGrab  = null;
    private ColorSensor sColor = null;
    private DistanceSensor sDist = null;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;
    static final double     WHEEL_DIAMETER_CM       = 4.0 ;
    static final double     COUNTS_PER_CM           = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_CM * Math.PI);

    @Override
    public void runOpMode() throws InterruptedException
    {

        //Setup Hardware
        mechGrab = hardwareMap.get(Servo.class, "GR");

        sColor = hardwareMap.get(ColorSensor.class, "CLR");
        sDist = hardwareMap.get(DistanceSensor.class, "CLR");


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

        double  cR=0,
                cG=0,
                cB=0;


        // run until the end of the match (driver presses STOP)

        Drive(0,.5,0,700);





        cR = sColor.red();
        cG = sColor.green();
        cB = sColor.blue();

        // Send calculated power to wheels

        drivePID(1,1,0,0,12000);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        telemetry.addData("IN", "Y (%.2f), X (%.2f)", vY, vX);
        telemetry.addData("Color", "R%.2f\nG%.2f\nB%.2f", cR, cG, cB);
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

    public void drivePID(double speed,
                         double X,
                         double Y,
                         double rot,
                         double timeout){
        int TargetFL;
        int TargetFR;
        int TargetRL;
        int TargetRR;

        if(opModeIsActive()){
            TargetFL = drive.FL.getCurrentPosition() + (int)(Y * COUNTS_PER_CM);
            TargetFR = drive.FR.getCurrentPosition() + (int)(Y * COUNTS_PER_CM);
            TargetRL = drive.RL.getCurrentPosition() + (int)(Y * COUNTS_PER_CM);
            TargetRR = drive.RR.getCurrentPosition() + (int)(Y * COUNTS_PER_CM);

            drive.FL.setTargetPosition(TargetFL);
            drive.FR.setTargetPosition(TargetFR);
            drive.RL.setTargetPosition(TargetRL);
            drive.RR.setTargetPosition(TargetRR);


        }

    }

    public void Stop() throws InterruptedException
    {
        Drive (0,0,0,0);
    }
}