package ro.ieti.licentaBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ieti.licentaBE.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
