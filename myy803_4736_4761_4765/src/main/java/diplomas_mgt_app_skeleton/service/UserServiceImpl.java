package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.dao.UserDAO;
import diplomas_mgt_app_skeleton.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO userDAO;

    public UserServiceImpl() {
        super();
    }

    @Autowired
    public UserServiceImpl(UserDAO theDAO){
        userDAO = theDAO ;
    }

    @Override
    @Transactional
    public void saveUser(User theUser) {
        String encodedPassword = bCryptPasswordEncoder.encode(theUser.getPassword());
        theUser.setPassword(encodedPassword);
        userDAO.save(theUser);
    }

    @Override
    @Transactional
    public boolean isUserPresent(User theUser){
        Optional<User> storedUser = userDAO.findByUsername(theUser.getUsername());
        return storedUser.isPresent();
    }

    @Override
    @Transactional
    public User findById(int us_id) {
        User user = userDAO.findById(us_id);
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("USER NOT FOUND", username)
                ));
    }
}
