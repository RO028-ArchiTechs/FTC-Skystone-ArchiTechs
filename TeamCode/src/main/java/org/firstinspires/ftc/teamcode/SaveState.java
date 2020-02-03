package org.firstinspires.ftc.teamcode;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

///saves the state of inputs on the gamepad
public class SaveState {
    public double left_trigger;
    public boolean left_bumper;
    double left_stick_y;
    double left_stick_x;
    double right_stick_y;
    double right_stick_x;
    double direction;
    double red;
    double green;
    double blue;
    boolean a;
    boolean b;
    boolean x;
    boolean y;
    boolean dpad_up;
    boolean dpad_down;
    double distance;
    double time;

    protected final static String dir = Environment.getExternalStorageDirectory().getPath() + "/" + "FIRST_";
    protected final static String file = "save1";

    SaveState(){
        left_stick_y = -1;
        left_stick_x = -1;
        right_stick_y = -1;
        right_stick_x = -1;
        direction = -1;
        red = -1;
        green = -1;
        blue = -1;
        a = false;
        b = false;
        x = false;
        y = false;
        dpad_up = false;
        dpad_down = false;
        distance = -1;
        time = -1;
        left_bumper = false;
        left_trigger = 0;
    }

    public boolean is_same(OutputSaveState other) {
        return this.left_stick_y == other.left_stick_y &&
        this.left_stick_x == other.left_stick_x &&
        this.right_stick_y == other.right_stick_y &&
        this.right_stick_x == other.right_stick_x &&
        this.direction == other.direction &&
        this.red == other.red &&
        this.green == other.green &&
        this.blue == other.blue &&
        this.a == other.a &&
        this.b == other.b &&
        this.x == other.x &&
        this.y == other.y &&
        this.dpad_up == other.dpad_up &&
        this.dpad_down == other.dpad_down &&
        this.distance == other.distance &&
        this.left_bumper == other.left_bumper &&
        this.left_trigger == other.left_trigger;
    }

    public void update(OutputSaveState tmp) {
        this.time = tmp.time;
    }
}
