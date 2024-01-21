package rm.solution.domain.subscription;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SubscriptionRequest {


    private Long id;
    @NotNull
    private String memberId;

    @Min(1)
    @Max(4)
    private int memberCount;
    @NotNull
    private ServiceType serviceType;

    private String company;
    private String phoneNum;
    private String address;


}
