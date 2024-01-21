package rm.solution.service;

import rm.solution.domain.member.Member;
import rm.solution.domain.subscription.UsingMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MemberServiceInterface {
    List<UsingMember> usingMember = new ArrayList<>();

    //아이디 중복체크
    boolean isDuplicated(String memberId);

    //비밀번호 암호화

    //void encodingPassword(Member member);

    //회원가입 저장
    Member add(Member member);


    //전체 회원 찾기
    List<Member> getAllMember();

    //특정 회원 찾기
    Optional<Member> findById(Long id);

    //
}
