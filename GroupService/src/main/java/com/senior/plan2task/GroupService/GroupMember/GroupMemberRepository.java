package com.senior.plan2task.GroupService.GroupMember;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupMemberRepository extends MongoRepository<GroupMember, String> {
    public List<GroupMember> findByGroupId(String groupId);
    public List<GroupMember> findByUserId(String userId);
}