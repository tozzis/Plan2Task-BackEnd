package com.senior.plan2task.MoodService.MoodType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MoodTypeService {

    @Autowired
    MoodTypeRepository moodTypeRepository;

    
    public List<MoodType> getAllMoodTypes() {
        return moodTypeRepository.findAll();
    }

    // public MoodType getMoodTypeImageByMoodId(String moodId) {
    //     return moodTypeRepository.findMoodTypeImageByMoodId(moodId);
    // }
    // public MoodType getMoodTypeImageById(String moodTypeId){
    //     return moodTypeRepository.findMoodTypeImageById(moodTypeId);

    // }

    public MoodType createMoodType(MoodType moodType) {
        return moodTypeRepository.save(moodType);
    }
    public MoodType getMoodTypeById(String moodTypeId) {
        return moodTypeRepository.findById(moodTypeId).get();

    }

}