package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.teamcode.SaveState;
import org.firstinspires.ftc.teamcode.EncodeController;

import java.io.IOException;

///it inherits SaveState to make sure that the directory in which we save is the same for the decoder
///and there is a need for 2 different save states because i don't know how openning a file simultaneously for writing and reading works
public class InputSaveState extends SaveState{

    In in;

    public InputSaveState() {
        super();
    }

    public void init_in() {
        in = new In(dir,file);
    }

    public boolean is_invalid() {
        return time == -1;
    }

    public void read() {

        in.update();
        left_stick_y = in.getDouble();
        left_stick_x = in.getDouble();
        right_stick_y = in.getDouble();
        right_stick_x = in.getDouble();
        direction = in.getDouble();
        red = in.getDouble();
        green = in.getDouble();
        blue = in.getDouble();
        a = in.getBoolean();
        b = in.getBoolean();
        x = in.getBoolean();
        y = in.getBoolean();
        dpad_up = in.getBoolean();
        dpad_down = in.getBoolean();
        distance = in.getDouble();
        time = in.getDouble();
    }
}
