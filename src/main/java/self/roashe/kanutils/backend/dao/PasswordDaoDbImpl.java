package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordDaoDbImpl implements PasswordDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String getUsername() {


        return jdbcTemplate.queryForObject("SELECT username FROM password", String.class);
    }

    @Override
    public String getPassword() {
        return "";
    }
}
