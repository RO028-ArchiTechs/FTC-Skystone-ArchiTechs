package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;


///Base class of various types of controll bindings
public class GeneralController {
    private ElapsedTime runtime = new ElapsedTime();

    private LinearOpMode cur_opmode; /// refference to the opmode that calls this controller
    private ColorSensor sColor = null;
    private DistanceSensor sDist = null;
    private ModernRoboticsI2cCompassSensor sCompass = null;

    public GeneralController(LinearOpMode opmode){
        ///setup Hardware
        cur_opmode = opmode; /// as far as i'm concerned, this should make cur_opmode behave like a C/C++ pointer, not a copy of the object
        ///sColor = cur_opmode.hardwareMap.get(ColorSensor.class, "CLR");
        ///sDist = cur_opmode.hardwareMap.get(DistanceSensor.class, "CLR");
        ///sCompass = cur_opmode.hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "HDG");
    }

    public double get_left_stick_y() {
        return cur_opmode.gamepad1.left_stick_y;
    }

    public double get_left_stick_x() {
        return cur_opmode.gamepad1.left_stick_x;
    }

    public double get_right_stick_y() {
        return cur_opmode.gamepad1.right_stick_y;
    }

    public double get_right_stick_x() {
        return cur_opmode.gamepad1.right_stick_x;
    }

    public double getDirection() {
        ///return (double)sCompass.getDirection();
            return -1;

    }

    public double red() {
    //    return sColor.red();
        return -1;
    }

    public double green() {
     //   return sColor.green();
        return -1;
    }

    public double blue() {
     //   return sColor.blue();
        return -1;
    }

    public boolean get_x() {
        return cur_opmode.gamepad1.x;
    }

    public boolean get_y() {
        return cur_opmode.gamepad1.y;
    }

    public boolean get_a() {
        return cur_opmode.gamepad1.a;
    }

    public boolean get_b() {
        return cur_opmode.gamepad1.b;
    }

    public void reset_runtime() {
        runtime.reset();
    }

    public ElapsedTime get_runtime() {
        return runtime;
    }

    public boolean get_dpad_up() {
        return cur_opmode.gamepad1.dpad_up;
    }

    public boolean get_dpad_down() {
        return cur_opmode.gamepad1.dpad_down;
    }

    public double getDistance(DistanceUnit dist_unit) {
///        return sDist.getDistance(dist_unit);
        return -1;
    }
}
