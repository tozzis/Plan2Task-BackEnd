package com.senior.plan2task.GroupService.GroupMember;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.senior.plan2task.GroupService.Filter.TokenAuthenticationService;
import com.senior.plan2task.GroupService.Group.Group;
import com.senior.plan2task.GroupService.Group.GroupService;
import com.senior.plan2task.GroupService.Model.GroupMemberResponse;
import com.senior.plan2task.GroupService.User.UserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupMemberController {
    @Autowired
    private UserAdapter userAdapter;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @GetMapping("/groups")
    public ResponseEntity<List<GroupMemberResponse>> getGroupsByUserId(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<GroupMember> groupMember = groupMemberService.getGroupsByUserId(userId);
        if (!groupMember.isEmpty()) {
            List<GroupMemberResponse> groupMemberResponse = new ArrayList<>();
            for (int i = 0; i < groupMember.size(); i++) {
                groupMemberResponse.add(new GroupMemberResponse(groupMember.get(i).getId(),
                        groupMember.get(i).getGroupId(), groupMember.get(i).getGroupPosition(),
                        userAdapter.getUserById(request, groupMember.get(i).getUserId())));
            }
            return new ResponseEntity<>(groupMemberResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

}