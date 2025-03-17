package com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.MemberManagementSubDomain.businesslogiclayer.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseModel>> getAllMembers() {
        List<MemberResponseModel> members = memberServiceImpl.getAllMembers();
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseModel> getMemberById(@PathVariable String memberId) {
        MemberResponseModel member = memberServiceImpl.getMemberById(memberId);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping
    public ResponseEntity<MemberResponseModel> addMember(@RequestBody MemberRequestModel newMemberData) {
        MemberResponseModel member = memberServiceImpl.addMember(newMemberData);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseModel> updateMember(@PathVariable String memberId, @RequestBody MemberRequestModel newMemberData) {
        MemberResponseModel updatedMember = memberServiceImpl.updateMember(memberId, newMemberData);
        return ResponseEntity.ok().body(updatedMember);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMemberById(@PathVariable String memberId) {
        String response = memberServiceImpl.deleteMemberById(memberId);
        return ResponseEntity.ok().body(response);
    }
}
