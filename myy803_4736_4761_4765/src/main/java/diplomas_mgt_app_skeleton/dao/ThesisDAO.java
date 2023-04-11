package diplomas_mgt_app_skeleton.dao;

import diplomas_mgt_app_skeleton.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisDAO extends JpaRepository<Thesis, Integer> {

    public Thesis findById(int th_id);
}
