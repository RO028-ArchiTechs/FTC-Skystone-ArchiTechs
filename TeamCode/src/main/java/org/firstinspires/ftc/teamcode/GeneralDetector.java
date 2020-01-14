package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Core;
import org.opencv.core.Size;
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

    Scalar opencv_low = new Scalar(25,160,160);
    Scalar opencv_high = new Scalar(32,255,255);

    static int difference_treshold = 0;

    Mat mask;

    @Override
    public Mat processFrame(Mat input)
    {
      //  Imgproc.cvtColor(input, mask, Imgproc.COLOR_RGB2HSV);
       // Core.inRange(mask,opencv_low,opencv_high,mask);
       // Imgproc.GaussianBlur(mask,mask,new Size(3,3),0,0);
        return input;
    }
}
