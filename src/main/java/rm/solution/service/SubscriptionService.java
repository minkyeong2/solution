package rm.solution.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.solution.domain.subscription.Subscription;
import rm.solution.domain.subscription.SubscriptionRequest;
import rm.solution.domain.subscription.SubscriptionStatus;
import rm.solution.domain.subscription.UsingMember;
import rm.solution.dto.SubscriptionResponseDTO;
import rm.solution.repository.mybatis.SubscriptionMapper;
import rm.solution.repository.mybatis.SubscriptionStatusMapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
@Service
public class SubscriptionService implements SubscriptionServiceInterface {

    private Subscription subscription;
    private SubscriptionStatus subscriptionStatus;


    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionStatusMapper subscriptionStatusMapper;

    @Autowired
    public SubscriptionService(SubscriptionMapper subscriptionMapper, SubscriptionStatusMapper subscriptionStatusMapper) {
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionStatusMapper = subscriptionStatusMapper;
    }


    //서비스 신청
    @Override
    public SubscriptionResponseDTO add(SubscriptionRequest subscriptionRequest) {
        Subscription subscription = subscriptionRequestToSubscription(subscriptionRequest);
        subscriptionMapper.addSubscription(subscription);

        return new SubscriptionResponseDTO(
                subscription.getId(),
                subscription.getMemberId(),
                subscription.getMemberCount(),
                subscription.getServiceType(),
                subscription.getCompany(),
                subscription.getPhoneNum(),
                subscription.getAddress()
        );

    }

    private Subscription subscriptionRequestToSubscription(SubscriptionRequest subscriptionRequest) {
        return new Subscription(
                subscriptionRequest.getId(),
                subscriptionRequest.getMemberId(),
                subscriptionRequest.getMemberCount(),
                subscriptionRequest.getServiceType(),
                subscriptionRequest.getCompany(),
                subscriptionRequest.getPhoneNum(),
                subscriptionRequest.getAddress()
        );
    }

    @Transactional
    public void addSubscriptionWithStatus(Subscription subscription) {
        subscriptionMapper.addSubscription(subscription);

        SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
        //subscriptionStatus.setId(subscription.getId());
        subscriptionStatus.setTotalStorage(subscription.getServiceType().getStorageCapacity());
        subscriptionStatus.setUsedStorage(0);
        double remainingStorage = subscription.getServiceType().getStorageCapacity() - subscriptionStatus.getUsedStorage();
        subscriptionStatus.setRemainingStorage(remainingStorage);
        subscriptionStatus.setSubStartDate(LocalDate.now());
        subscriptionStatus.setSubEndDate(LocalDate.now().plusYears(1));
        subscriptionStatus.setServiceType(subscription.getServiceType());


        subscriptionStatusMapper.save(subscriptionStatus);

    }

    //멤버추가
    @Override
    public void addMember(UsingMember usingmember) {

    }

    //남은기간
    @Override
    public double getRemainingStorage() {
        return Math.max(0, subscriptionStatus.getTotalStorage() - subscriptionStatus.getUsedStorage());
    }

    @Override
    public Period getRemainingPeriod() {
        if (subscriptionStatus.getSubEndDate() == null || LocalDate.now().isAfter(subscriptionStatus.getSubEndDate())) {
            return Period.ZERO;
        }
        return Period.between(LocalDate.now(), subscriptionStatus.getSubEndDate());
    }

    @Override
    public void extendService(Long id) {

        //id로 subscribe가져오기
        SubscriptionStatus subscriptionStatus = subscriptionStatusMapper.findSubscriptionById(id);



        if (subscriptionStatus != null && subscriptionStatus.getSubEndDate() != null && LocalDate.now().isBefore(subscriptionStatus.getSubEndDate())) {

            subscriptionStatus.setTotalStorage(subscriptionStatus.getTotalStorage() + subscriptionStatus.getTotalStorage());
            subscriptionStatus.setRemainingStorage(subscriptionStatus.getRemainingStorage() + subscriptionStatus.getRemainingStorage());

            subscriptionStatus.setSubEndDate(subscriptionStatus.getSubEndDate().plusYears(1));

            subscriptionStatusMapper.extend(subscriptionStatus);
            log.info("서비스 기간이 1년 연장되었습니다. 서비스 종료일: " + subscriptionStatus.getSubEndDate());
        } else {
            log.info("서비스 연장이 불가합니다.");
        }}}
