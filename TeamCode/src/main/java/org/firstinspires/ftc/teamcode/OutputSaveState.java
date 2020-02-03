package org.firstinspires.ftc.teamcode;
import android.content.Context;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.SaveState;
import org.firstinspires.ftc.teamcode.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.InputStream;
import java.io.OutputStream;

///it inherits SaveState to make sure that the directory in which we save is the same for the encoder
///and there is a need for 2 different save states because i don't know how openning a file simultaneously for writing and reading works
public class OutputSaveState extends SaveState{

    Log out = null;

    public OutputSaveState(EncodeController controller) {
        this.left_stick_y = controller.get_left_stick_y();
        this.left_stick_x = controller.get_left_stick_x();
        this.right_stick_y = controller.get_right_stick_y();
        this.right_stick_x = controller.get_right_stick_x();
        this.direction = controller.getDirection();
        this.red = controller.red();
        this.green = controller.green();
        this.blue = controller.blue();
        this.a = controller.get_a();
        this.b = controller.get_b();
        this.x = controller.get_x();
        this.y = controller.get_y();
        this.dpad_up = controller.get_dpad_up();
        this.dpad_down = controller.get_dpad_down();
        this.distance = controller.getDistance(DistanceUnit.CM);
        this.time = controller.get_runtime().milliseconds();
        this.left_bumper = controller.get_left_bumper();
        this.left_trigger = controller.get_left_trigger();
    }

    public void change(OutputSaveState other) {

        this.left_stick_y = other.left_stick_y;
        this.left_stick_x = other.left_stick_x;
        this.right_stick_y = other.right_stick_y;
        this.right_stick_x = other.right_stick_x;
        this.direction = other.direction;
        this.red = other.red;
        this.green = other.green;
        this.blue = other.blue;
        this.a = other.a;
        this.b = other.b;
        this.x = other.x;
        this.y = other.y;
        this.dpad_up = other.dpad_up;
        this.dpad_down = other.dpad_down;
        this.distance = other.distance;
        this.time = other.time;
        this.left_bumper = other.left_bumper;
        this.left_trigger = other.left_trigger;
    }


    public void init_out() {
        this.out = new Log(dir,file,false);
    }

    public void write() {
        out.addData(left_stick_y);
        out.addData(left_stick_x);
        out.addData(right_stick_y);
        out.addData(right_stick_x);
        out.addData(direction);
        out.addData(red);
        out.addData(green);
        out.addData(blue);
        out.addData(a);
        out.addData(b);
        out.addData(x);
        out.addData(y);
        out.addData(dpad_up);
        out.addData(dpad_down);
        out.addData(distance);
        out.addData(time);
        out.addData(left_bumper);
        out.addData(left_trigger);
        out.update();
    }

    public void write_final() {
        this.write();
        out.close();
        out = null;
    }
}
