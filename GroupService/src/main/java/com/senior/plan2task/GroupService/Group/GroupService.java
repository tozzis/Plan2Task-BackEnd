package com.senior.plan2task.GroupService.Group;

import com.senior.plan2task.GroupService.Group.Model.GroupResponse;
import com.senior.plan2task.GroupService.Group.Model.Member;
import com.senior.plan2task.GroupService.GroupMember.GroupMember;
import com.senior.plan2task.GroupService.GroupMember.GroupMemberService;
import com.senior.plan2task.GroupService.User.User;
import com.senior.plan2task.GroupService.User.UserAdapter;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private UserAdapter userAdapter;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private GroupMemberService groupMemberService;
    
    public List<GroupResponse> getGroup(HttpServletRequest request, String userId){
        List<GroupMember> groupMembers = groupMemberService.getGroupMemberByUserId(userId);
        List<GroupResponse> groupResponses = new ArrayList<>(); 
        for (GroupMember groupMembersData : groupMembers) {
            Group group = groupRepository.findById(groupMembersData.getGroupId()).get();
            List<GroupMember> groupMember = groupMemberService.getGroupMemberByGroupId(group.getId());
            List<Member> member = new ArrayList<>(); 
            for (GroupMember groupMemberData : groupMember) {
                User user = userAdapter.getUserById(request, groupMemberData.getUserId());
                member.add(new Member(user, groupMemberData.getPosition()));
            }
            GroupResponse sent = new GroupResponse(group.getId(), group.getGroupName(), group.getGroupImage(), member);
            groupResponses.add(sent);
        }
        return groupResponses;
    }
    
    public GroupResponse getGroupById(HttpServletRequest request, String id){
        Group group = groupRepository.findById(id).get();
        List<GroupMember> groupMember = groupMemberService.getGroupMemberByGroupId(group.getId());
        List<Member> member = new ArrayList<>(); 
        for (GroupMember groupMemberData : groupMember) {
            User user = userAdapter.getUserById(request, groupMemberData.getUserId());
            member.add(new Member(user, groupMemberData.getPosition()));
        }
        return new GroupResponse(group.getId(), group.getGroupName(), group.getGroupImage(), member);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }
    public void deleteGroupById(String id){
        groupRepository.deleteById(id);
    }
    public Group getGroupByIdN(String id){
        return groupRepository.findById(id).get();
    }

}