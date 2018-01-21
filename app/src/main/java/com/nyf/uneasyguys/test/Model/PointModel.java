package com.nyf.uneasyguys.test.Model;

/**
 * Created by sesan on 2017-12-30.
 */

public class PointModel {
    long point_x;
    long point_y;

    /*
 point_x = ((long) (location.getLongitude()* 100));
            point_y = ((long) (location.getLatitude()* 100));

    */
    public PointModel(long point_x, long point_y){
        this.point_x = point_x;
        this.point_y = point_y;
    }
    public PointModel(double latitude, double longitude){
        this.point_x = (long)(longitude * 1000000000);
        this.point_y = (long)(latitude * 1000000000);
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
    public long getPointXDown(){return (long)Math.floor(((double)point_x)/10000)*10000; }
    public long getPointXUp(){return (long)Math.ceil(((double)point_x)/10000)*10000;}
    public long getPointYDown(){return (long)Math.floor(((double)point_y)/10000)*10000;}
    public long getPointYUp(){return (long)Math.ceil(((double)point_y)/10000)*10000;}


    public long getPointX(){return point_x;}
    public long getPointY(){return point_y;}
    public long getPoint(){return point_x * 1000000000 + point_y;}
    public double getLongitude(){ return ((double)point_x) / 1000000;   }
    public double getLatitude(){return   ((double)point_y) / 1000000;}
}
