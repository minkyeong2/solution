package rm.solution.service;

import rm.solution.domain.member.Member;

public interface LoginServiceInterface {
    Member login(String memberId, String password);
}
