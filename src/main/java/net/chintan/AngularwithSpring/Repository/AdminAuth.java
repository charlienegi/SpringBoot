package net.chintan.AngularwithSpring.Repository;

import net.chintan.AngularwithSpring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAuth extends JpaRepository<User,Integer>{
    User findByemail(String email);
    User findByid(Integer id);

}
