package rm.solution.service;

import rm.solution.domain.subscription.Subscription;
import rm.solution.domain.subscription.SubscriptionRequest;
import rm.solution.domain.subscription.UsingMember;
import rm.solution.dto.SubscriptionResponseDTO;

import java.time.Period;
import java.util.List;

public interface SubscriptionServiceInterface {
    SubscriptionResponseDTO add(SubscriptionRequest subscriptionRequest);

    //서비스에 멤버 추가(최대4명)
    void addMember(UsingMember usingMember);

    //남은 용량
    double getRemainingStorage();

    //남은 기간
    Period getRemainingPeriod();

    //서비스 기간 연장
    void extendService(Long id);


}
