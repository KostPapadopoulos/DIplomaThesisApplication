package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Subject;

import java.util.List;

public interface SubjectService {

    public void save(Subject theSubject);

    public List<Subject> findAll();

    public Subject findById(int theId);

    public void deleteById(int theId);
}
