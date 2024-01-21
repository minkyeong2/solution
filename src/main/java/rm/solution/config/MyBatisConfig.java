package rm.solution.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rm.solution.repository.mybatis.MemberMapper;
import rm.solution.service.MemberService;
import rm.solution.service.MemberServiceInterface;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MemberMapper memberMapper;

    @Bean
    public MemberServiceInterface memberServiceInterface(){

        return new MemberService();
    }





}
