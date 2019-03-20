package com.senior.plan2task.MoodService.MoodType;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoodTypeRepository extends MongoRepository<MoodType, String> {
    MoodType findMoodTypeImageByMoodId(String mood_id);
}

