package com.senior.plan2task.GroupService.Group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {

        return groupRepository.findAll();
    }

    
    public Group getGroupById(String groupId) {
        return groupRepository.findById(groupId).get();
    }

    public void delete(Group group) {
        groupRepository.delete(group);
    }


}