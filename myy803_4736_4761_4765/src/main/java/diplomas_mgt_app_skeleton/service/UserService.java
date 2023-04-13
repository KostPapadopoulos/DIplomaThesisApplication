package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    public void saveUser(User theUser);

    public boolean isUserPresent(User theUser);

    public User findById(int us_id);

}
