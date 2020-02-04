package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;


@TeleOp(name="~v1.0", group="Linear Opmode")
//@Disabled
public class vOnePointOh extends LinearOpMode {

    // Declare Hardware, timing, etc
    private ElapsedTime runtime = new ElapsedTime();
    HardwareDrivetrain drive = new HardwareDrivetrain();
    private DcMotor mechExt = null;
    private Servo  mechGrab = null;


    @Override
    public void runOpMode() {

        //Setup Hardware
        drive.init(hardwareMap);
        mechGrab = hardwareMap.get(Servo.class, "GR");
        mechExt = hardwareMap.get(DcMotor.class, "EXT");




        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //INPUT
        double vX, vY, vR;     //robot speed as commanded by driver (can be in either LOCAL or GLOBAL coordinates, depending on the MODE)
        double Ext;
        //SENSOR INPUT
        double  cR=0,
                cG=0,
                cB=0;

        //CTRL
        double delay = 0 ;          //stiu ca e cancer da atat s-a putut
        int MODE = 0;
        int GEAR = 1;
        double Gear_shift = 0;
                            /* MODE 0 means simple "first person" controls, no trig tricks needed,
                             MODE 1 means controlling the robot in GLOBAL coordinates
                             (left's always left, right's always right, regardless of robot orientation)(some trig required)
                             see MODE STATE MACHINE */
        double speed;
        double LocalX = 0, LocalY = 0; //robot speed in local coordinates
        double X=0, Y=0, A=0;
        double A0 =0;
        int ExtDirection;
        //OUTPUT
        double  power_FL, power_FR,

                power_RL, power_RR;
        double power_Ext;

        mechGrab.setPosition(1.0);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //READ INPUTS
            vY = -gamepad1.left_stick_y;
            vX = gamepad1.left_stick_x;
            vR = gamepad1.right_stick_x;
            Ext = gamepad1.left_trigger;




            if(gamepad1.right_bumper){
                speed = 0.40;
            } else {
                speed = 1.0;
            }


            if(gamepad1.dpad_up){
                mechGrab.setPosition(0.5);
            } else if (gamepad1.dpad_down) {
                mechGrab.setPosition(1.0);
            }


            if(gamepad1.left_bumper){
                ExtDirection = -1;
            }   else  {
                ExtDirection = 1;
            }

            power_Ext = Ext * speed * ExtDirection;



            //Apply speed modifier
            vX *= speed;
            vY *= speed;
            vR *= speed;
            /*
            switch (MODE) {
                case 0: {
                    LocalY = vY;
                    LocalX = vX;
                    break;
                }

                case 1: {
                    LocalX = vX * Math.cos(A) - vY * Math.sin(A);
                    LocalY = vX * Math.sin(A) + vY * Math.cos(A);
                    break;
                }
            }

            */

            ///*
                LocalY = vY;
                LocalX = vX;
            //*/

            // O D O M E T R Y goes HERE !!

            power_FL = Range.clip(+LocalX + LocalY + vR, -1.0, 1.0);
            power_FR = Range.clip(-LocalX + LocalY - vR, -1.0, 1.0);
            power_RL = Range.clip(-LocalX + LocalY + vR, -1.0, 1.0);
            power_RR = Range.clip(+LocalX + LocalY - vR, -1.0, 1.0);


            // Send calculated power to wheels
            drive.FL.setPower(power_FL);
            drive.FR.setPower(power_FR);
            drive.RL.setPower(power_RL);
            drive.RR.setPower(power_RR);
            mechExt.setPower(power_Ext);
            

            // Telemetry feedback
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("MODE", ": (%d)", MODE);
            telemetry.addData("GEAR", ": %.2f", Gear_shift);
            telemetry.addData("IN","X%.2f Y%.2f",LocalX,LocalY);
            telemetry.addData("Motors", "FL (%.2f), FR (%.2f), RL (%.2f), RR (%.2f)", power_FL, power_FR, power_RL, power_RR);

            telemetry.update();

        }
    }
}