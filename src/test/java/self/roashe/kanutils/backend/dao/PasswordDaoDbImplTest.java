package self.roashe.kanutils.backend.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import self.roashe.kanutils.backend.TestApplicationConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class PasswordDaoDbImplTest {
    @Autowired
    PasswordDao dao;

    @Test
    void test(){
        System.out.println(dao.getUsername());
    };

}