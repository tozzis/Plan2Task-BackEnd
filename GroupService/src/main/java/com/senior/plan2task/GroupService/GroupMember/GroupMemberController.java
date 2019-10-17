package com.senior.plan2task.GroupService.GroupMember;

import com.senior.plan2task.GroupService.Filter.TokenAuthenticationService;
import com.senior.plan2task.GroupService.Group.Group;
import com.senior.plan2task.GroupService.Group.GroupService;
import com.senior.plan2task.GroupService.Group.Model.GroupMemberRequest;
import com.senior.plan2task.GroupService.Group.Model.GroupResponse;
import com.senior.plan2task.GroupService.Group.Model.Member;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupMemberController {
    
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @GetMapping("/group/member/{id}")
    public ResponseEntity<List<Member>> getGroupMember(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        return new ResponseEntity<>(groupMemberService.getMemberByUserId(request, id, userId), HttpStatus.OK);
    }
    
    @PostMapping("/group/member/{groupId}")
    public ResponseEntity<Boolean> saveGroupMember(HttpServletRequest request, @PathVariable String groupId, @RequestBody GroupMemberRequest groupMemberRequest) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        GroupResponse group = groupService.getGroupById(request, groupId);
        boolean checkUserMember = false;
        for (int i = 0; i < group.getMember().size(); i++) {
            if(userId.equals(group.getMember().get(i).getUser().getId())){
                checkUserMember = true;
                break;
            }
        }
        if(checkUserMember == true){
            List<GroupMember> groupMembers = groupMemberService.getGroupMemberByGroupId(groupId);
            String userLeader = userId;
            for(int i = 0; i < groupMembers.size(); i++){
                if(!"leader".equals(groupMembers.get(i).getPosition()) && !groupMembers.get(i).getUserId().equals(userId) ){
                    groupMemberService.deleteGroupMember(groupMembers.get(i).getId());
                }else if("leader".equals(groupMembers.get(i).getPosition())){
                    userLeader = groupMembers.get(i).getUserId();
                }
            }
            for (int i = 0; i < groupMemberRequest.getGroupMemberStatus().size(); i++) {
                if(!groupMemberRequest.getGroupMemberStatus().get(i).getUserId().equals(userLeader) && !groupMemberRequest.getGroupMemberStatus().get(i).getUserId().equals(userId)){
                    if(groupMemberRequest.getGroupMemberStatus().get(i).isStatus() == true){
                        groupMemberService.saveGroupMember(new GroupMember(null, groupId, "member", groupMemberRequest.getGroupMemberStatus().get(i).getUserId()));
                    }
                }
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can not save member in this group !!!");
        }
    }
}