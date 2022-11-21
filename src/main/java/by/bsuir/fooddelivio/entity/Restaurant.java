package by.bsuir.fooddelivio.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String workTime;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Menu> menus;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Review> reviews;
}
