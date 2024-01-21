package rm.solution;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class passwordTest {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Test
    void password(){
        String pass = "asdfghjk";
        String encodedPass = passwordEncoder.encode(pass);
        System.out.println(encodedPass);
    }

    @Test
    void test1(){
        String pass1 = "asdfghjk";
        String enco1 = "$2a$10$lAMME7VOcPajfLsUkIoZHujHSrsOcLCKhmZBFXWJgj0sqvn86Grga";

        Assertions.assertThat(passwordEncoder.matches(pass1 ,enco1)).isTrue();
    }
}
