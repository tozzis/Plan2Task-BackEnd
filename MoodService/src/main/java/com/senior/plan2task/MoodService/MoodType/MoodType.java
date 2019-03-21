package com.senior.plan2task.MoodService.MoodType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senior.plan2task.MoodService.Mood.Mood;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "moodtypes")

public class MoodType {

    @Id
    private String id;

    private String moodTypeName;

    private String MoodTypeImage;



    public MoodType() {
    }

    public MoodType(String id, String moodTypeName, String MoodTypeImage) {
        this.id = id;
        this.moodTypeName = moodTypeName;
        this.MoodTypeImage = MoodTypeImage;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoodTypeName() {
        return this.moodTypeName;
    }

    public void setMoodTypeName(String moodTypeName) {
        this.moodTypeName = moodTypeName;
    }

    public String getMoodTypeImage() {
        return this.MoodTypeImage;
    }

    public void setMoodTypeImage(String MoodTypeImage) {
        this.MoodTypeImage = MoodTypeImage;
    }



}
