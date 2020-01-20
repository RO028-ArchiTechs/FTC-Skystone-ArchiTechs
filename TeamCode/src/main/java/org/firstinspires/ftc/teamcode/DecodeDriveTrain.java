package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import org.firstinspires.ftc.teamcode.DecodeController;

import java.util.Locale;


@TeleOp(name="#T>DecodeDrivetrain", group="Linear Opmode")
//@Disabled
public class DecodeDriveTrain extends LinearOpMode {

    // Declare Hardware, timing, etc
    private DcMotor driveFL = null;
    private DcMotor driveFR = null;
    private DcMotor driveRL = null;
    private DcMotor driveRR = null;
    private Servo mechGrab = null;


    @Override
    public void runOpMode() {
        ///setup controller
        DecodeController controller = new DecodeController(this);

        //Setup Hardware
        driveFL = hardwareMap.get(DcMotor.class, "FL");
        driveFR = hardwareMap.get(DcMotor.class, "FR");
        driveRL = hardwareMap.get(DcMotor.class, "RL");
        driveRR = hardwareMap.get(DcMotor.class, "RR");
        mechGrab = hardwareMap.get(Servo.class, "GR");


        driveFL.setDirection(DcMotor.Direction.FORWARD);
        driveFR.setDirection(DcMotor.Direction.REVERSE);
        driveRL.setDirection(DcMotor.Direction.FORWARD);
        driveRR.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        controller.reset_runtime();

        //INPUT
        double vX, vY, vR;     //robot speed as commanded by driver (can be in either LOCAL or GLOBAL coordinates, depending on the MODE)

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

        double LocalX = 0, LocalY = 0; //robot speed in local coordinates
        double X=0, Y=0, A=0;
        double A0 =0;
        //OUTPUT
        double  power_FL, power_FR,

                power_RL, power_RR;



        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //READ INPUTS
            controller.read_state();
            if(controller.is_invalid()){
                break;
            }
            vY = -controller.get_left_stick_y();
            vX = controller.get_left_stick_x();
            vR = controller.get_right_stick_x();

            A = A0 + Math.toRadians(controller.getDirection());

            cR = controller.red();
            cG = controller.green();
            cB = controller.blue();

            if (controller.get_runtime().seconds()-delay > .3){
                if(controller.get_x()){
                    GEAR ++;

                } else if( GEAR > 4) {
                    GEAR = 1;
                }
                if(controller.get_y()) {
                    if(MODE == 0){
                        MODE = 1;
                    }
                    else if(MODE ==1 ){
                        MODE = 0;
                    }
                }
                if(controller.get_x() && controller.get_b()){
                    A0 = A;
                }
                delay = controller.get_runtime().seconds();
            }

            if(controller.get_dpad_up()){
                mechGrab.setPosition(0);
            } else if (controller.get_dpad_down()) {
                mechGrab.setPosition(1);
            }
            switch (GEAR){
                case 1 : {
                    Gear_shift = .25;
                    break;
                }
                case 2 : {
                    Gear_shift = .4;
                    break;
                }
                case 3 : {
                    Gear_shift = .65;
                    break;
                }
                case 4 : {
                    Gear_shift = 1;
                    break;
                }
            }

            //Apply GEAR
            vX *= Gear_shift;
            vY *= Gear_shift;
            vR *= Gear_shift;

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

            // O D O M E T R Y goes HERE !!

            power_FL = Range.clip(+LocalX + LocalY + vR, -1.0, 1.0);
            power_FR = Range.clip(-LocalX + LocalY - vR, -1.0, 1.0);
            power_RL = Range.clip(-LocalX + LocalY + vR, -1.0, 1.0);
            power_RR = Range.clip(+LocalX + LocalY - vR, -1.0, 1.0);


            // Send calculated power to wheels
            driveFL.setPower(power_FL);
            driveFR.setPower(power_FR);
            driveRL.setPower(power_RL);
            driveRR.setPower(power_RR);


            // Telemetry feedback
            telemetry.addData("Status", "Run Time: " + controller.get_runtime().toString());
            telemetry.addData("MODE", ": (%d)", MODE);
            telemetry.addData("GEAR", ": %.2f", Gear_shift);
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", controller.getDistance()));
            telemetry.addData("Color", "\nR%.2f\nG%.2f\nB%.2f", cR, cG, cB);
            telemetry.addData("HDG","%.2f",A);
            telemetry.addData("IN","X%.2f Y%.2f",LocalX,LocalY);
            telemetry.addData("Motors", "FL (%.2f), FR (%.2f), RL (%.2f), RR (%.2f)", power_FL, power_FR, power_RL, power_RR);

            telemetry.update();
        }
    }
}