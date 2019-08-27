package com.senior.plan2task.GroupService.Group;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.senior.plan2task.GroupService.Filter.TokenAuthenticationService;
import com.senior.plan2task.GroupService.GroupMember.GroupMember;
import com.senior.plan2task.GroupService.GroupMember.GroupMemberService;
import com.senior.plan2task.GroupService.Model.GroupResponse;
import com.senior.plan2task.GroupService.Model.Member;
import com.senior.plan2task.GroupService.User.UserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private UserAdapter userAdapter;

    @GetMapping("/groupssss")
    public ResponseEntity<List<GroupResponse>> getGroupsByUserId(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        System.out.println("UserId =" + userId);
        List<GroupMember> groupMember = groupMemberService.getGroupsByUserId(userId);
        List<Group> groupList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();

        for (int i = 0; i < groupMember.size(); i++) {
            String groupId = groupMember.get(i).getGroupId();
            groupList.add(groupService.getGroupById(groupId));
            for (int j = 1; j < groupMember.size(); j++) {
                System.out.println("UserId {I} =" + groupMember.get(i).getUserId());
                System.out.println("UserId {J}=" + groupMember.get(j).getUserId());

                if (groupMember.get(i).getUserId().equals(groupMember.get(j).getUserId())) {
                    System.out.println("eieasdsf");

                } else {

                    memberList.add(new Member(userAdapter.getUserById(request, groupMember.get(i).getUserId()),
                            groupMember.get(i).getGroupPosition()));
                }
            }
        }

        if (!groupList.isEmpty()) {
            List<GroupResponse> groupResponse = new ArrayList<>();
            for (int i = 0; i < groupList.size(); i++) {
                groupResponse.add(new GroupResponse(groupList.get(i).getId(), groupList.get(i).getGroupName(),
                        groupList.get(i).getGroupImage(), memberList));
            }
            return new ResponseEntity<>(groupResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    // Create a new Group
    @PostMapping("/group")
    public ResponseEntity<Group> createGroup(@Valid @RequestBody Group group, HttpServletRequest request) {
        Group group_object = groupService.createGroup(group);
        String userId = tokenAuthenticationService.getUserByToken(request);
        GroupMember groupMember = new GroupMember();
        groupMember.setUserId(userId);
        groupMember.setGroupId(group.getId());
        groupMember.setGroupPosition("Leader");
        groupMemberService.saveGroup(groupMember);

        return new ResponseEntity<>(group_object, HttpStatus.OK);
    }

    // Get a Single Group
    @GetMapping("/group/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") String id) {
        // return userRepository.findById(userId).orElseThrow(() -> new
        // ResourceNotFoundException("User", "id", userId));

        Group group = groupService.getGroupById(id);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Update a Group
    @PutMapping("/group/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable(value = "id") String groupId,
            @Valid @RequestBody Group groupDetails) {

        Group group = groupService.getGroupById(groupId);

        group.setGroupName(groupDetails.getGroupName());
        group.setGroupImage(groupDetails.getGroupImage());

        Group updatedGroup = groupService.createGroup(group);
        return new ResponseEntity<Group>(updatedGroup, HttpStatus.OK);
    }

    // Delete a Group
    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") String groupId) {
        Group group = groupService.getGroupById(groupId);
        groupService.delete(group);
        return ResponseEntity.ok().build();
    }

}