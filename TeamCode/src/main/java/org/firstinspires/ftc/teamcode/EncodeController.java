package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.GeneralController;
import org.firstinspires.ftc.teamcode.InputSaveState;

public class EncodeController extends GeneralController {

    OutputSaveState last;

    public EncodeController(LinearOpMode opmode) {
        super(opmode);
        last = new OutputSaveState(this);
        last.init_out();
    }

    public void write_state() {
        OutputSaveState tmp = new OutputSaveState(this);
        if(last.is_same(tmp) == false){
            last.write();
            last.change(tmp);
        }
        else {
            last.update(tmp);
        }
        tmp = null;
    }

    public void write_final_state() {
        last.write_final();
        last = null;
    }
}
