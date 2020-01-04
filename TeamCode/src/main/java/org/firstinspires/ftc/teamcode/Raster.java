package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pixel_t;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

///Useful information:
///-our phone's camera takes 2448 x 3264 images
///Really dont know why i cant pass bigger resolutions than 1200 x 1600
///0-indexed

public class Raster {
    int h,w;///height and width of raster
    pixel_t pixels[][];

    Raster() {
        h = -1;
        w = -1;
        pixels = new pixel_t[1][1];
    }

    Raster(int h,int w){
        this.h = h;
        this.w = w;
        pixels = new pixel_t[h][w];
        for(int i = 0;i < h;i++){
            for(int j = 0;j < w;j++){
                pixels[i][j].setPos(i,j);
            }
        }
    }

    pixel_t getPixel(int i,int j){
        return pixels[i][j];
    }
    void setPixel(int i,int j,pixel_t val){
        pixels[i][j] = val;
    }

    Raster getSubsection(int x1,int y1,int x2,int y2){
        Raster ans = new Raster(x2 - x1,y2 - y1);
        for(int i = x1;i < x2;i++) {
            for (int j = y1; j < y2; j++) {
                ans.pixels[i - x1][j - y1] = this.pixels[i][j];
            }
        }
        return ans;
    }

    void print_to_log() {
        System.out.println("printing...");
        for(int i = 0;i < h;i++){
            for(int j = 0;j < w;j++){
                System.out.println(String.valueOf(pixels[i][j].i) + String.valueOf(pixels[i][j].j) + String.valueOf(pixels[i][j].r) + String.valueOf(pixels[i][j].g) + String.valueOf(pixels[i][j].b));
            }
        }
    }
}
