package rm.solution.domain.subscription;


import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
public class Subscription {
    private Long id;
    //서비스 신청한 사람
    private String memberId;
    //private List<UsingMember> usingMembers;
    private int memberCount;
    private ServiceType serviceType;

    private SubscriptionStatus subscriptionStatus;

    private String company;
    private String phoneNum;
    private String address;

    public Subscription(Long id,String memberId,  int memberCount, ServiceType serviceType, String company, String phoneNum, String address) {
        this.id=id;
        this.memberId=memberId;
        this.memberCount = memberCount;
        this.serviceType = serviceType;
        this.company = company;
        this.phoneNum = phoneNum;
        this.address = address;
    }


}
