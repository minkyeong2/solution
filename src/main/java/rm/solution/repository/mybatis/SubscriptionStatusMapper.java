package rm.solution.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import rm.solution.domain.subscription.SubscriptionStatus;

@Mapper
public interface SubscriptionStatusMapper {

    void save(SubscriptionStatus subscriptionStatus);
    void extend(SubscriptionStatus subscriptionStatus);
    SubscriptionStatus findSubscriptionById(Long id);
}
