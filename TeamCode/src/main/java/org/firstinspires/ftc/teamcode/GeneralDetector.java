package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

class GeneralDetector extends OpenCvPipeline
{
    /*
     * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
     * highly recommended to declare them here as instance variables and re-use them for
     * each invocation of processFrame(), rather than declaring them as new local variables
     * each time through processFrame(). This removes the danger of causing a memory leak
     * by forgetting to call mat.release(), and it also reduces memory pressure by not
     * constantly allocating and freeing large chunks of memory.
     */

    Scalar low = new Scalar(87,60,15);
    Scalar high = new Scalar(216,186,94);
    static int difference_treshold = 0;

    @Override
    public Mat processFrame(Mat input)
    {
        ///TODO actually process the frame
        return input;
    }
}
