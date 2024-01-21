package rm.solution.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import rm.solution.domain.member.Member;
import rm.solution.repository.mybatis.MemberMapper;


import java.util.List;
import java.util.Optional;

@Service
@Primary
@Slf4j
public class MemberService implements MemberServiceInterface {


    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService() {
    }


    //아이디 중복 체크
    @Override
    public boolean isDuplicated(String memberId) {
        Optional<Member> findMember = memberMapper.findByMemberId(memberId);
        if(findMember.isPresent()){
            log.info("사용 불가한 ID입니다.");
            return true;
        }else{
            log.info("사용 가능한 ID입니다.");
            return false;
        }

    }


    @Override
    public Member add(Member member) {
        String encodedPassword = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberMapper.add(member);
        return member;
    }



    @Override
    public List<Member> getAllMember() {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

}
