package by.bsuir.fooddelivio.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menus")
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    private List<Dish> dishes;
}
