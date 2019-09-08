package com.senior.plan2task.GroupService.Group;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.senior.plan2task.GroupService.Filter.TokenAuthenticationService;
import com.senior.plan2task.GroupService.Group.Model.GroupResponse;
import com.senior.plan2task.GroupService.GroupMember.GroupMember;
import com.senior.plan2task.GroupService.GroupMember.GroupMemberService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupController {

    @Autowired
    private GroupService groupService;
    
    @Autowired
    private GroupMemberService groupMemberService;
    
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @GetMapping("/group")
    public ResponseEntity<List<GroupResponse>> getGroups(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request); 
        return new ResponseEntity<>(groupService.getGroup(request, userId), HttpStatus.OK);
    }
    
    @GetMapping("/group/{id}")
    public ResponseEntity<GroupResponse> getGroups(HttpServletRequest request, @PathVariable String id) {
        return new ResponseEntity<>(groupService.getGroupById(request, id), HttpStatus.OK);
    }
    
    @PostMapping("/group")
    public ResponseEntity<Group> createGroup(@Valid @RequestBody Group group, HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request); 
        group.setGroupImage("http://moneyhub.in.th/wp-content/uploads/2015/11/shutterstock_287274665.jpg");
        groupService.saveGroup(group);
        GroupMember groupMember = new GroupMember(null, group.getId(), "leader", userId);
        groupMemberService.saveGroupMember(groupMember);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

}