package ro.ieti.licentaBE.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private  String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private boolean active;

    private Date createdTime;

    @Enumerated(EnumType.STRING)
    private AuthEnum authProvider;

    @OneToOne
    @JoinColumn(name = "user_role", nullable = false)
    private UserRole userRole;
    
}
