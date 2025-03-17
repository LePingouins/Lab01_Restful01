package com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findMemberByMemberIdentifier(MemberIdentifier memberIdentifier);
    // Method to find member by memberId directly
    @Query("SELECT m FROM Member m WHERE m.memberIdentifier.memberId = :memberId")
    Member findMemberByMemberId(@Param("memberId") String memberId);


}
