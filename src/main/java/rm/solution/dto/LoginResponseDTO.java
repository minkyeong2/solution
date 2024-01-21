package rm.solution.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponseDTO {

    private Long id;
    private String memberId;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public LoginResponseDTO(Long id, String memberId, String name, String email, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }


}
