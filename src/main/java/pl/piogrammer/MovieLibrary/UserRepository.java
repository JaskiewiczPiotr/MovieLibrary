package pl.piogrammer.MovieLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.piogrammer.MovieLibrary.model.User;

import java.util.List;
@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers(){
        return jdbcTemplate.query("SELECT id_user, user_name, mail, password FROM user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public int saveUser(List<User> users) {
        users.forEach(user -> jdbcTemplate.update("" +
                "INSERT INTO user(user_name, mail, password) VALUES (?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getMail()));
        return 1;
    }

}
