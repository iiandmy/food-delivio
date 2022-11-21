package by.bsuir.fooddelivio.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String deliveryAddress;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Review> reviews;
}
