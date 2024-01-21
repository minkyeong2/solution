package rm.solution.domain.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginMember {

    private String memberId;
    private String password;
}
