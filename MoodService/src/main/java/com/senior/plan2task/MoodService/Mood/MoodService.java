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

    public Mood getMoodById(String mood_id) {
        return moodRepository.findById(mood_id)
                .orElseThrow(() -> new ResourceNotFoundException("Mood", "id", mood_id));

    }

    public MoodType getMoodTypeImageByMoodId(String mood_id) {

        return moodTypeService.getMoodTypeImageByMoodId(mood_id);

    }

}