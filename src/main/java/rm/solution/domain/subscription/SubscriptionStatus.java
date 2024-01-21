package rm.solution.domain.subscription;

import lombok.Data;
import lombok.Getter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SubscriptionStatus {
    private Long id;
    private double totalStorage;
    private double usedStorage;
    private double remainingStorage;
    private LocalDate subStartDate;
    private LocalDate subEndDate;
    private ServiceType serviceType;

    public ServiceType getServiceType() {
        return serviceType;
    }
}
