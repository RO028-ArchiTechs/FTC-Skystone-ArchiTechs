package org.firstinspires.ftc.teamcode;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

//has a field for each possible input(so far)
//it is used for replicating driver behavior
//it also has a working directory and a file name stored, so we can assure that the replicator reads and writes to the same file via inheritance
public class SaveState {
    public double left_trigger;//gamepad left trigger variable
    public boolean left_bumper;//gamepad left bumper variable
    public boolean right_bumper;//gamepad right bumper variable
    public double right_trigger;//gamepad right trigger variable
    public boolean dpad_right;//gamepad dpad right variable
    public boolean dpad_left;//gamepad dpad left variable
    double left_stick_y;//gamepad left stick y variable
    double left_stick_x;//gamepad left stick x variable
    double right_stick_y;//gamepad right stick y variable
    double right_stick_x;//gamepad right stick x variable
    double direction;//compass sensor direction variable
    double red;//color sensor red variable
    double green;//color sensor green variable
    double blue;//color sensor blue variable
    boolean a;//gamepad a button variable
    boolean b;//gamepad b button variable
    boolean x;//gamepad x button variable
    boolean y;//gamepad y button variable
    boolean dpad_up;//gamepad dpad up variable
    boolean dpad_down;//gamepad dpad down variable
    double distance;//distance sensor distance variable
    double time;//the time at which this savestate was created

    protected final static String dir = Environment.getExternalStorageDirectory().getPath() + "/" + "FIRST_";//save state working directory
    protected final static String file = "save1";//save state name

    SaveState(){
        //intializing variables with neutral values
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
        dpad_left = false;
        dpad_right = false;
        right_bumper = false;
        right_trigger = 0;
    }

    public boolean is_same(OutputSaveState other) {//checks if a savestate is the same with another one(with no regard to time)
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
        this.left_trigger == other.left_trigger &&
        this.dpad_left == other.dpad_left &&
        this.dpad_right == other.dpad_right &&
        this.right_bumper == other.right_bumper &&
        this.right_trigger == other.right_trigger;
    }

    public void update(OutputSaveState tmp) {//this is for quick changes between save states which differ only by time
        this.time = tmp.time;
    }
}
