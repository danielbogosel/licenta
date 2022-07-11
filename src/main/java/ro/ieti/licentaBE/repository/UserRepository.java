package ro.ieti.licentaBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ieti.licentaBE.entity.CustomerUser;

@Repository
public interface UserRepository extends JpaRepository<CustomerUser, Long> {

    CustomerUser getCustomerUserByEmail(String email);
}
