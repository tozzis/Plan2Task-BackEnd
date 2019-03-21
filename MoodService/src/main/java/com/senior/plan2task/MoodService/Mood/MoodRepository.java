package com.senior.plan2task.MoodService.Mood;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoodRepository extends MongoRepository<Mood, String> {

    public List<Mood> findByUserId(String userId);

}

