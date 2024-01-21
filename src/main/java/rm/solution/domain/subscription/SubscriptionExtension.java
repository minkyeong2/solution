package rm.solution.domain.subscription;

import lombok.Data;
import rm.solution.domain.subscription.ServiceType;
import rm.solution.domain.subscription.Subscription;

@Data
public class SubscriptionExtension {

    private Long id;
    private Subscription subscription;
    private int extensionPeriod;
}
