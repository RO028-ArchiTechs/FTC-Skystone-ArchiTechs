package org.firstinspires.ftc.teamcode;

import android.hardware.Camera;
import android.util.Log;



import android.Manifest;
import android.view.View;

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

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;



@Autonomous(name="T>AutoCam", group="Linear Opmode")
@Disabled
public class ACam extends LinearOpMode {

    // Declare Hardware, timing, etc
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException
    {

        //Setup Hardware

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //INPUT

        telemetry.addData("trying to capture","");


        //snap photo

        telemetry.addData("captured ?","");

        // run until the end of the match (driver presses STOP)




        // Send calculated power to wheels


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());


        //telemetry.addData("Motors", "FL (%.2f), FR (%.2f), RL (%.2f), RR (%.2f)", power_FL, power_FR, power_RL, power_RR);

        telemetry.update();


    }

    private boolean safeCameraOpen(int id) {
        boolean qOpened = false;

        try {
            releaseCameraAndPreview();
            Camera camera = Camera.open(id);
            qOpened = (camera != null);
        } catch (Exception e) {
            Log.e(""+R.string.app_name, "failed to open Camera");
            e.printStackTrace();
        }

        return qOpened;
    }

    private void releaseCameraAndPreview() {
/*
        View preview.setCamera(null);
        if (camera != null) {
            camera.release();
            camera = null;
        }
*/
    }





}