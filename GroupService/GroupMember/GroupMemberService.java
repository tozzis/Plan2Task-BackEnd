package com.senior.plan2task.GroupService.GroupMember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public List<GroupMember> getGroupsByUserId(String userId) {
        return groupMemberRepository.findByUserId(userId);
    }
        
    public void saveGroup(GroupMember groupMember){
        groupMemberRepository.save(groupMember);
   }
}