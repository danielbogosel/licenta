package ro.ieti.licentaBE.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String deliveryAddress;

    private OrderStatusEnum orderStatus;

    private Integer quantity;

    private Date createdDate;

    private Integer orderPrice;

    @ManyToMany
    @JoinColumn(name = "product_id",nullable = false)
    Set<Product> products;

    @OneToMany
    @JoinColumn(name = "user_id",nullable = false)
    Set<CustomerUser> customerUsers;


}
