package rm.solution.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.solution.domain.member.Member;
import rm.solution.dto.LoginResponseDTO;
import rm.solution.repository.mybatis.MemberMapper;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class LoginService {


    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public LoginResponseDTO login(String memberId, String password) {
        Optional<Member> newMember = memberMapper.findByMemberId(memberId);
        if (newMember.isEmpty()){
            return null;
        }

        Member loginMember = newMember.get();

        if(bCryptPasswordEncoder.matches(password, loginMember.getPassword())){

          return memberMapper.findLoginResponseByMemberId(memberId);

        }
        return null;

    }


}
