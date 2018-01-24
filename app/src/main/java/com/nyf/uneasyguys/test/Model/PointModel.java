package com.nyf.uneasyguys.test.Model;

/**
 * Created by sesan on 2017-12-30.
 */

public class PointModel {
    long point_x_long;
    long point_y_long;
    long point;
    double point_x;
    double point_y;
    int danger;
    int safe;

    /*
 point_x_long = ((long) (location.getLongitude()* 100));
            point_y_long = ((long) (location.getLatitude()* 100));

    */
    public PointModel(long point_x_long, long point_y_long){
        this.point_x_long = point_x_long;
        this.point_y_long = point_y_long;
        this.point = point_x_long * 1000000000 + point_y_long;
        this.point_x = (double)point_x_long / 1000000;
        this.point_y = (double)point_y_long / 1000000;
    }
    public PointModel(double latitude, double longitude){
        this.point_x_long = (long)(longitude * 1000000);
        this.point_y_long = (long)(latitude * 1000000);
        this.point = point_x_long * 1000000000 + point_y_long;
        this.point_x = latitude;
        this.point_y = longitude;
    }


    public long getPointN(){
        return getPointXDown() * 1000000000 + getPointYDown();
    }
    public long getPointE(){
        return getPointXUp() * 1000000000 + getPointYDown();
    }
    public long getPointW(){
        return getPointXDown() * 1000000000 + getPointYUp();
    }
    public long getPointS(){
        return getPointXUp() * 1000000000 + getPointYUp();
    }
    public long getPointXDown(){return (long)Math.floor(((double)point_x_long)/10000)*10000; }
    public long getPointXUp(){return (long)Math.ceil(((double)point_x_long)/10000)*10000;}
    public long getPointYDown(){return (long)Math.floor(((double)point_y_long)/10000)*10000;}
    public long getPointYUp(){return (long)Math.ceil(((double)point_y_long)/10000)*10000;}


    public long getPoint_x_long() {return point_x_long;}
    public long getPoint_y_long() {return point_y_long;}
    public double getPoint_x() {return point_x;}
    public double getPoint_y() {return point_y;}
    public int getDanger() {return danger;}
    public int getSafe() {return safe;}
    public long getPoint(){return point;}
    public double getLongitude(){ return point_x;   }
    public double getLatitude(){return point_y;}
}
