package com.champsoft.Lab01_Restful.MemberManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer.Member;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer.MemberIdentifier;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer.MemberRepository;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.datamapperlayer.MemberRequestMapper;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.datamapperlayer.MemberResponseMapper;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer.MemberRequestModel;
import com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer.MemberResponseModel;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberRequestMapper memberRequestMapper;
    private final MemberResponseMapper memberResponseMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             MemberRequestMapper memberRequestMapper,
                             MemberResponseMapper memberResponseMapper) {
        this.memberRepository = memberRepository;
        this.memberRequestMapper = memberRequestMapper;
        this.memberResponseMapper = memberResponseMapper;
    }

    @Override
    public List<MemberResponseModel> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return memberResponseMapper.entityListToResponseModelList(members);
    }

    @Override
    public MemberResponseModel getMemberById(String memberId) {
        Member member = this.memberRepository.findMemberByMemberIdentifier(memberId);
        if (member == null) {
            throw new NotFoundException("Member with id: " + memberId + " not found.");
        }
        return this.memberResponseMapper.entityToResponseModel(member);
    }

    @Override
    public MemberResponseModel addMember(MemberRequestModel newMemberData) {
        // Générer un identifiant unique si `memberId` est null ou vide
        String memberId = (newMemberData.getMemberId() == null || newMemberData.getMemberId().isEmpty())
                ? UUID.randomUUID().toString()
                : newMemberData.getMemberId();

        MemberIdentifier memberIdentifier = new MemberIdentifier(memberId);

        // Vérifier si le membre existe déjà
        Member foundMember = this.memberRepository.findMemberByMemberIdentifier(memberIdentifier);
        if (foundMember != null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " already exists.");
        }

        // Mapper la requête vers une entité et lui affecter un identifiant
        Member member = this.memberRequestMapper.requestModelToEntity(newMemberData);
        member.setMemberIdentifier(memberIdentifier);

        // Sauvegarder l'entité dans la base de données
        Member savedMember = this.memberRepository.save(member);

        // Retourner la réponse
        return this.memberResponseMapper.entityToResponseModel(savedMember);
    }

    @Override
    public MemberResponseModel updateMember(String memberId, MemberRequestModel newMemberData) {
        MemberIdentifier memberIdentifier = new MemberIdentifier(memberId);
        Member foundMember = this.memberRepository.findMemberByMemberIdentifier(memberIdentifier);

        if (foundMember == null) {
            throw new NotFoundException("Member with id: " + memberId + " not found.");
        }

        // Mise à jour du membre
        Member member = this.memberRequestMapper.requestModelToEntity(newMemberData);
        member.setMemberIdentifier(memberIdentifier); // important
        member.setId(foundMember.getId()); // Réassigner l'ID existant au membre

        Member savedMember = this.memberRepository.save(member);
        return this.memberResponseMapper.entityToResponseModel(savedMember);
    }

    @Override
    public String deleteMemberById(String memberId) {
        MemberIdentifier memberIdentifier = new MemberIdentifier(memberId);
        Member foundMember = this.memberRepository.findMemberByMemberIdentifier(memberIdentifier);

        if (foundMember == null) {
            return "Member with id: " + memberId + " not found.";
        } else {
            this.memberRepository.delete(foundMember);
            return "Member with id: " + memberId + " deleted successfully.";
        }
    }
}
