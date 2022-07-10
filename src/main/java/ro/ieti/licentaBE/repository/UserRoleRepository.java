package ro.ieti.licentaBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ieti.licentaBE.entity.CustomerUser;
import ro.ieti.licentaBE.entity.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<CustomerUser, Long> {

}
