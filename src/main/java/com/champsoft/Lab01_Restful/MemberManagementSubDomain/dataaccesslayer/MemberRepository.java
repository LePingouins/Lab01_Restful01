package com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findMemberByMemberIdentifier(MemberIdentifier memberIdentifier);
    Member findMemberByMemberIdentifier(String memberId);


}
