package ro.ieti.licentaBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ieti.licentaBE.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
