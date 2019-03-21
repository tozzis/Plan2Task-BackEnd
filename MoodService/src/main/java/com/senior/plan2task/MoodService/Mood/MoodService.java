package com.senior.plan2task.MoodService.Mood;

import java.util.List;

import com.senior.plan2task.MoodService.MoodType.MoodType;
import com.senior.plan2task.MoodService.MoodType.MoodTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MoodService {

    @Autowired
    MoodRepository moodRepository;

    @Autowired
    MoodTypeService moodTypeService;

    public List<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

    public List<Mood> getMoodByUserId(String userId) {
        return moodRepository.findByUserId(userId);
    }

    public Mood getMoodById(String moodId) {
        return moodRepository.findById(moodId).orElseThrow(() -> new ResourceNotFoundException("Mood", "id", moodId));

    }

    // public MoodType getMoodTypeImageByMoodId(String moodId) {

    //     return moodTypeService.getMoodTypeImageByMoodId(moodId);

    // }

    public void createMood(Mood mood) {
        moodRepository.save(mood);
    }

}