package by.bsuir.fooddelivio.entity;

import lombok.*;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "dishes")
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int calories;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
