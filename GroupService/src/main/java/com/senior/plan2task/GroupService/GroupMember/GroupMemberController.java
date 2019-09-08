package com.senior.plan2task.GroupService.GroupMember;

import com.senior.plan2task.GroupService.Filter.TokenAuthenticationService;
import com.senior.plan2task.GroupService.Group.GroupService;
import com.senior.plan2task.GroupService.Group.Model.Member;
import com.senior.plan2task.GroupService.User.UserAdapter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @GetMapping("/group/member/{id}")
    public ResponseEntity<List<Member>> getGroupMember(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request); 
        return new ResponseEntity<>(groupMemberService.getMemberByUserId(request, id, userId), HttpStatus.OK);
    }
}