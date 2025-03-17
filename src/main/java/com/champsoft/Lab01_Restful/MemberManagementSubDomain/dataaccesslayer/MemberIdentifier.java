package com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class MemberIdentifier {
    @Column(name = "member_id")
    private String memberId;

    public MemberIdentifier(String memberId) {
        this.memberId = memberId;
    }

    public static MemberIdentifier generate() {
        return new MemberIdentifier(UUID.randomUUID().toString());
    }
}
