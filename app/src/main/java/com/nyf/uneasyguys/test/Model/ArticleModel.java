package com.nyf.uneasyguys.test.Model;

import java.util.Date;

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
    public int getId(){ return  id;}
    public UserModel getOwner(){ return  owner;}
    public String getCreatedTime(){ return created_time;}
    public String getUpdatedTime(){return updated_time;}
    public String getText(){return text;}

}
