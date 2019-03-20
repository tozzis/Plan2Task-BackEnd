package com.senior.plan2task.MoodService.Mood;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoodRepository extends MongoRepository<Mood, String> {
}

