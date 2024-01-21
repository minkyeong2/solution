package rm.solution.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import rm.solution.domain.member.Member;
import rm.solution.dto.LoginResponseDTO;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    //회원 추가
    void add(Member member);
    Optional<Member> findByMemberId(String memberId);
    LoginResponseDTO findLoginResponseByMemberId(String memberId);
}
