package com.champsoft.Lab01_Restful.MemberManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer.Member;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer.MemberRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberRequestMapper {

    @Mapping(target = "memberIdentifier.memberId", source ="memberId" )
    @Mapping(target = "id", ignore = true) // L'id sera généré automatiquement
    @Mapping(target = "memberAddress.street", source = "street")
    @Mapping(target = "memberAddress.city", source = "city")
    @Mapping(target = "memberAddress.province", source = "province")
    @Mapping(target = "memberAddress.country", source = "country")
    Member requestModelToEntity(MemberRequestModel memberRequestModel);

    List<Member> requestModelListToEntityList(List<MemberRequestModel> memberRequestModel);
}
