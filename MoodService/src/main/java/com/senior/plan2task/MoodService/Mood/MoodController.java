package com.senior.plan2task.MoodService.Mood;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.senior.plan2task.MoodService.Filter.TokenAuthenticationService;
import com.senior.plan2task.MoodService.MoodType.MoodType;
import com.senior.plan2task.MoodService.MoodType.MoodTypeService;
import com.senior.plan2task.MoodService.User.User;
import com.senior.plan2task.MoodService.User.UserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MoodController {

    @Autowired
    private UserAdapter userAdapter;
    
    @Autowired
    MoodService moodService;

    @Autowired
    MoodTypeService MoodTypeService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @GetMapping("/moods")
    public ResponseEntity<List<Mood>> getAllMoods() {
        List<Mood> mood = moodService.getAllMoods();
        return new ResponseEntity<>(mood, HttpStatus.OK);
    }

    // @GetMapping(path = "/moods/images/{moodId}")
    // public ResponseEntity<MoodType> getMoodTypeImageByMoodId(@PathVariable("moodId") String moodId) {
    //     MoodType moodType = moodService.getMoodTypeImageByMoodId(moodId);
    //     return new ResponseEntity<>(moodType, HttpStatus.OK);
    // }

    @GetMapping(path = "/moods/me")
    public ResponseEntity<List<Mood>> getMoodByUserId(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Mood> moods = moodService.getMoodByUserId(userId);

        return new ResponseEntity<>(moods, HttpStatus.OK);

    }

    @GetMapping(path = "/moods/{moodId}")
    public ResponseEntity<Mood> getMoodById(@PathVariable("moodId") String moodId) {
        Mood mood = moodService.getMoodById(moodId);
        return new ResponseEntity<>(mood, HttpStatus.OK);

    }

    @PostMapping("/mood")
    public ResponseEntity<Mood> AddMood(HttpServletRequest request, @RequestBody Mood mood) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        mood.setUserId(userId);
        mood.setMoodTypeId(mood.getMoodTypeId());
        moodService.createMood(mood);
        return new ResponseEntity<>(mood, HttpStatus.OK);
    }



}