package com.senior.plan2task.GroupService.GroupMember;

import com.senior.plan2task.GroupService.Group.Model.Member;
import com.senior.plan2task.GroupService.User.User;
import com.senior.plan2task.GroupService.User.UserAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {
    
    @Autowired
    private UserAdapter userAdapter;

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    
    public List<GroupMember> getGroupMemberByGroupId(String groupId){
        return groupMemberRepository.findByGroupId(groupId);
    }
    
    public List<GroupMember> getGroupMemberByUserId(String userId){
        return groupMemberRepository.findByUserId(userId);
    }
    
    public List<Member> getMemberByUserId(HttpServletRequest request, String groupId, String userId){
        List<GroupMember> groupMember = getGroupMemberByGroupId(groupId);
        List<Member> member = new ArrayList<>();
        for (GroupMember groupMemberData : groupMember) {
            User user = userAdapter.getUserById(request, groupMemberData.getUserId());
            member.add(new Member(user, groupMemberData.getPosition()));
        }
        return member;
    }
        
    public void saveGroupMember(GroupMember groupMember){
        groupMemberRepository.save(groupMember);
   }
}