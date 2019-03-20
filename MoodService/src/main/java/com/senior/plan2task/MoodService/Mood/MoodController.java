package com.senior.plan2task.MoodService.Mood;

import java.util.List;

import com.senior.plan2task.MoodService.MoodType.MoodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MoodController {

    @Autowired
    MoodService moodService;

    @GetMapping("/moods")
    public ResponseEntity<List<Mood>> getAllMoods() {
        List<Mood> mood = moodService.getAllMoods();
        return new ResponseEntity<List<Mood>>(mood, HttpStatus.OK);
    }

    @RequestMapping(path = "/moods/{mood_id}/images")
    public ResponseEntity<MoodType> getMoodTypeImageByMoodtId(@PathVariable("mood_id") String mood_id) {
        MoodType moodType = moodService.getMoodTypeImageByMoodId(mood_id);
        return new ResponseEntity<MoodType>(moodType, HttpStatus.OK);
    }

    @GetMapping(path = "/moods/{mood_id}")
    public ResponseEntity<Mood> getMoodById(@PathVariable("mood_id") String mood_id) {
        Mood mood = moodService.getMoodById(mood_id);
        return new ResponseEntity<Mood>(mood, HttpStatus.OK);

    }

}