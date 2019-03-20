package com.senior.plan2task.MoodService.MoodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MoodTypeService {

    @Autowired
    MoodTypeRepository MoodTypeRepository;

    public MoodType getMoodTypeImageByMoodId(String mood_id) {
        return MoodTypeRepository.findMoodTypeImageByMoodId(mood_id);
    }

}