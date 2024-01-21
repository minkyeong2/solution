package rm.solution.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm.solution.domain.subscription.Subscription;
import rm.solution.domain.subscription.SubscriptionRequest;
import rm.solution.domain.subscription.UsingMember;
import rm.solution.dto.SubscriptionResponseDTO;
import rm.solution.service.SubscriptionService;
import rm.solution.service.SubscriptionServiceInterface;

@Slf4j
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService= subscriptionService;
    }

    @PostMapping("/add")
    public ResponseEntity<SubscriptionResponseDTO> addSubscription(@RequestBody @Valid SubscriptionRequest subscriptionRequest){

        SubscriptionResponseDTO subscriptionResponseDTO = subscriptionService.add(subscriptionRequest);
        return new ResponseEntity<>(subscriptionResponseDTO, HttpStatus.CREATED);
    }


    @PostMapping("/addMember")
    public ResponseEntity<Void> addSubscriptionMember(@RequestBody UsingMember usingMember){
        UsingMember usingSubMember = new UsingMember();
        usingSubMember.setId(usingMember.getId());
        usingSubMember.setMemberId(usingMember.getMemberId());
        usingSubMember.setName(usingMember.getMemberId());
        usingSubMember.setMemberCount(usingMember.getMemberCount());

        subscriptionService.addMember(usingMember);

        log.info("서비스 멤버 추가 성공");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addWithStatus")
    public ResponseEntity<String> addSubscriptionWithStatus(@RequestBody Subscription subscription){
        subscriptionService.addSubscriptionWithStatus(subscription);

        return ResponseEntity.ok("구독 정보 저장 성공");
    }


    @PostMapping("/extend/{id}")
    public ResponseEntity<String> extendSubscriptionService(@PathVariable(name = "id") Long id) {

        try {

            log.info("===========================");
            subscriptionService.extendService(id);
            log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            return ResponseEntity.ok("서비스 기간이 1년 연장되었습니다.");
        } catch (Exception e) {
            log.info("{}",e);
            return ResponseEntity.badRequest().body("서비스 연장이 불가합니다");

        }

    }

}

