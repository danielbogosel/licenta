package ro.ieti.licentaBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ieti.licentaBE.entity.CustomerUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomerUser, Long> {

    Optional<CustomerUser> findCustomerUserByEmail(String email);
}
