package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;

/**
 * inspired by
 * https://github.com/StPaulAcademy/HOMAR-FTC-Library/blob/master/src/main/java/edu/spa/ftclib/internal/Log.java
 */

public class In {
    private BufferedReader fileReader;
    private String line;
    private boolean disabled = false;
    In(String directoryPath,String filename) {
        File directory = new File(directoryPath);
        //noinspection ResultOfMethodCallIgnored
        directory.mkdir();
        try {
            fileReader = new BufferedReader(new FileReader(directoryPath+"/"+filename+".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void close() {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (disabled) return;
        try {
            line = fileReader.readLine();
            if(line == null){
                this.setDisabled(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSData() {
        if (disabled) return "-1";
        String ans = "";
        int id = 0;
        while(id < line.length() && (line.charAt(id) != ',') && (line.charAt(id) != '\n')){
            ans = ans + line.charAt(id);
            id++;
        }
        if(id + 1 >= line.length()){
            line = null;
        }
        else {
            line = line.substring(id + 1);
        }
        return ans;
    }

    public byte getByte() {
        return Byte.valueOf(getSData());
    }

    public Boolean getBoolean() {
        return Boolean.valueOf(getSData());
    }

    public short getShort() {
        return Short.valueOf(getSData());
    }

    public int getInt() {
        return Integer.valueOf(getSData());
    }

    public long getLong() {
        return Long.valueOf(getSData());
    }

    public float getFloat() {
        return Float.valueOf(getSData());
    }
    public double getDouble() {
        return Double.valueOf(getSData());
    }
}