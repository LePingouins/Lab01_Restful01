package com.champsoft.Lab01_Restful.MemberManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer.MemberRequestModel;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer.MemberResponseModel;

import java.util.List;

public interface MemberService {
    List<MemberResponseModel> getAllMembers();
    MemberResponseModel getMemberById(String memberId);

    MemberResponseModel addMember(MemberRequestModel newMemberData);

    MemberResponseModel updateMember(String memberId, MemberRequestModel newMemberData);

    String deleteMemberById(String memberId);
}
