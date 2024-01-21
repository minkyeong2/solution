package rm.solution.domain.subscription;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class UsingMember {

    @Min(1)
    @Max(4)
    private Long id;
    private String memberId;
    private String name;
    private int memberCount;




}
