package rm.solution.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rm.solution.domain.member.Member;
import rm.solution.service.MemberService;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //아이디 중복 체크
    @GetMapping("/checkId")
    public ResponseEntity<String> checkId(@RequestParam(name = "memberId") String memberId){
        log.info("아이디 중복 체크");

        boolean isDuplicated = memberService.isDuplicated(memberId);
        if(isDuplicated){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 id");
        }
        log.info("사용 가능한 ID입니다.");
        return ResponseEntity.ok("사용 가능한 id");

    }

    @PostMapping("/add")
    public ResponseEntity<String> addMember(@RequestBody Member member){
        try{

            boolean isDuplicated = memberService.isDuplicated(member.getMemberId());
            if(isDuplicated){

                log.info("이미 사용중인 ID입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 id");
            }

            memberService.add(member);
            log.info("회원가입 성공 = {}",member);
            return new ResponseEntity<>("add member success", HttpStatus.OK);
        }catch (Exception e){
            log.info("회원가입 실패",e);
            return new ResponseEntity<>("add member fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
