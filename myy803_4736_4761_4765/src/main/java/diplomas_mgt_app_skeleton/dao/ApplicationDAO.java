package diplomas_mgt_app_skeleton.dao;

import diplomas_mgt_app_skeleton.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDAO extends JpaRepository<Application, Integer> {

    public Application findById(int ap_id);
}
