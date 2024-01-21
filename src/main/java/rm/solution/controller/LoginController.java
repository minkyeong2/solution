package rm.solution.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rm.solution.domain.member.LoginMember;
import rm.solution.dto.LoginResponseDTO;
import rm.solution.service.LoginService;

@Slf4j
@Controller
//@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginMember loginMember){

        String memberId = loginMember.getMemberId();
        String password = loginMember.getPassword();

        LoginResponseDTO loginResult = loginService.login(memberId, password);
        log.info("memberId={}",memberId);
        log.info("password= {}", password);

        if(loginResult != null){
            return ResponseEntity.ok(loginResult);
        }
        log.info("로그인실패");
        return ResponseEntity.badRequest().body("로그인 실패");
    }
}
