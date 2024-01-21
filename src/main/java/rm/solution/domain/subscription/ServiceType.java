package rm.solution.domain.subscription;

import lombok.Getter;

@Getter
public enum ServiceType {
    BASIC("basic",2,100000),
    STANDARD("standard",5,200000),
    PREMIUM("premium",10,800000);

    private final String displayName;
    private final int storageCapacity;
    private final int subscriptionFee;


    ServiceType(String displayName, int storageCapacity, int subscriptionFee) {
        this.displayName = displayName;
        this.storageCapacity = storageCapacity;
        this.subscriptionFee = subscriptionFee;
    }
}
