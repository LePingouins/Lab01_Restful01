package com.champsoft.Lab01_Restful.MemberManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer.Member;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer.MemberResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberResponseMapper {

    @Mapping(target = "memberId", source = "memberIdentifier.memberId")
    @Mapping(target = "street", source = "memberAddress.street")
    @Mapping(target = "city", source = "memberAddress.city")
    @Mapping(target = "province", source = "memberAddress.province")
    @Mapping(target = "country", source = "memberAddress.country")
    MemberResponseModel entityToResponseModel(Member member);

    List<MemberResponseModel> entityListToResponseModelList(List<Member> members);
}
