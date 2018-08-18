package com.sesang06.retrofit.test.Model;

import com.sesang06.retrofit.test.Service.DateService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sesan on 2017-12-14.
 */

public class ArticleModel {
    private int id;
    private long point;
    private UserModel owner;
    private String text;
    private String created_time;
    private String updated_time;
    private String image;
    private String user;
    private long point_x;
    private long point_y;
    public String getUser(){
        return user;
    }
    public String getImage(){
        return image;
    }
    public int getId(){ return  id;}
    public UserModel getOwner(){ return  owner;}
    public String getCreatedTime(){
        return DateService.getUserFriendlyDate(created_time);
    }
    public String getUpdatedTime(){
        return  updated_time;
     }
    public String getText(){return text;}
    public long getPoint(){return point;}

    public long getPointX(){return point_x;}
    public long getPointY(){return point_y;}

    public ArticleModel(String text){
        this.text = text;
    }

}
