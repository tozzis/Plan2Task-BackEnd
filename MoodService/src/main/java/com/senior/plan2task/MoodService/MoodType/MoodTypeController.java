package com.senior.plan2task.MoodService.MoodType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MoodTypeController {

    @Autowired
    MoodTypeService moodTypeService;

    @GetMapping("/moodtypes")
    public ResponseEntity<List<MoodType>> getAllMoodTypes() {
        List<MoodType> mood = moodTypeService.getAllMoodTypes();
        return new ResponseEntity<>(mood, HttpStatus.OK);
    }

    @GetMapping(path = "/moodtypes/{moodTypeId}")
    public ResponseEntity<MoodType> getMoodTypeById(@PathVariable("moodTypeId") String moodTypeId) {
        MoodType moodType = moodTypeService.getMoodTypeById(moodTypeId);
        return new ResponseEntity<>(moodType, HttpStatus.OK);

    }

    @PostMapping("/moodtype")
    public ResponseEntity<MoodType> createMoodType(@RequestBody(required = true) MoodType moodType) {
        MoodType newMoodType = moodTypeService.createMoodType(moodType);

        return new ResponseEntity<>(newMoodType, HttpStatus.OK);
    }

}