package rm.solution.dto;

import lombok.Data;
import rm.solution.domain.subscription.ServiceType;

@Data
public class SubscriptionResponseDTO {

    private Long id;
    private int memberCount;
    private ServiceType serviceType;
    private String company;
    private String phoneNum;
    private String address;

    public SubscriptionResponseDTO(Long id, String memberId, int memberCount, ServiceType serviceType, String company, String phoneNum, String address) {
        this.id = id;
        this.memberCount = memberCount;
        this.serviceType = serviceType;
        this.company = company;
        this.phoneNum = phoneNum;
        this.address = address;
    }
}
