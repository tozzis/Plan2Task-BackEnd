package com.senior.plan2task.MoodService.Mood;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "moods")
public class Mood {

    @Id
    private String id;

    private String moodName;

    private Date moodDate;

    private String userId;

    private String moodTypeId;

    private String moodDetail;



    public Mood() {
    }

    public Mood(String id, String moodName,String moodDetail, Date moodDate,String userId,String moodTypeId) {
        this.id = id;
        this.moodName = moodName;
        this.moodDetail = moodDetail;
        this.moodDate = moodDate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoodName() {
        return this.moodName;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }

    public Date getMoodDate() {
        return this.moodDate;
    }

    public void setMoodDate(Date moodDate) {
        this.moodDate = moodDate;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoodTypeId() {
        return this.moodTypeId;
    }

    public void setMoodTypeId(String moodTypeId) {
        this.moodTypeId = moodTypeId;
    }

    public String getMoodDetail() {
        return this.moodDetail;
    }

    public void setMoodDetail(String moodDetail) {
        this.moodDetail = moodDetail;
    }
    

}
