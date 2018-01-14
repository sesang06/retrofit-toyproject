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
    public PointModel(long point){
        this.point_x = point / 1000000000;
        this.point_y = point %  1000000000;
    }

    public long getPointX(){return point_x;}
    public long getPointY(){return point_y;}
    public long getPoint(){return point_x * 1000000000 + point_y;}
    public double getLongitude(){ return ((double)point_x) / 100;   }
    public double getLatitude(){return   ((double)point_y) / 100;}
}
