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
        return jdbcTemplate.query("SELECT id_user, login, mail, password FROM user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public int saveUser(List<User> users) {
        users.forEach(user -> jdbcTemplate.update("" +
                "INSERT INTO user(login, mail, password) VALUES (?, ?, ?)",
                user.getLogin(),user.getMail(), user.getPassword()));
        return 1;
    }

    public int saveSingleUser(User user) {
        int rowsAffected = jdbcTemplate.update(
                "INSERT INTO user(login, mail, password) VALUES (?, ?, ?)",
                user.getLogin(),
                user.getMail(),
                user.getPassword()
        );
        return rowsAffected;
    }


}
