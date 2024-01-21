package rm.solution.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import rm.solution.domain.subscription.Subscription;
import rm.solution.domain.subscription.UsingMember;

@Mapper
public interface SubscriptionMapper {


    //서비스 신청
    void addSubscription(Subscription subscription);

    //사용 멤버 추가
    void addUsingMember(UsingMember usingMember);

    //ID로 사용중인 서비스 찾기
    Subscription findSubscriptionById(Long id);

    //MEMBER_ID로 사용중인 서비스 찾기
    Subscription findSubscriptionByMemberId(String memberId);
    //서비스 수정
    void updateSubscription(Subscription subscription);
    //서비스 연장
    void extendSubscription(Subscription subscription);
}
