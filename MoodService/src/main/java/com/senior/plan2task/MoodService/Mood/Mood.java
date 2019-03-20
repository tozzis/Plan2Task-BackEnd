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

    public Mood() {
    }

    public Mood(String id, String moodName, Date moodDate) {
        this.id = id;
        this.moodName = moodName;
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

}
