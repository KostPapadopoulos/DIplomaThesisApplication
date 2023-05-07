package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.UserDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Role;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
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

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private ProfessorDAO professorDAO;

    public UserServiceImpl() {
        super();
    }

    @Autowired
    public UserServiceImpl(UserDAO theDAO, StudentDAO theSDAO, ProfessorDAO thePDAO){
        this.userDAO = theDAO ;
        this.studentDAO = theSDAO;
        this.professorDAO = thePDAO;

    }

    @Override
    @Transactional
    public void saveUser(User theUser) {
        String encodedPassword = bCryptPasswordEncoder.encode(theUser.getPassword());
        theUser.setPassword(encodedPassword);
        userDAO.save(theUser);

        if (theUser.getRole() == Role.STUDENT){
            Student newStudent = new Student(theUser.getUsername());
            //newStudent.
            //newStudent.setUser(theUser);
            //newStudent.getUser().setUs_id(theUser.getUs_id());
            studentDAO.save(newStudent);
        }

        if (theUser.getRole() == Role.PROFESSOR){
            Professor newProfessor = new Professor(theUser.getUsername());
            //newProfessor.setUser(theUser);
            //newProfessor.getUser().setUs_id(theUser.getUs_id());
            professorDAO.save(newProfessor);
        }

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
