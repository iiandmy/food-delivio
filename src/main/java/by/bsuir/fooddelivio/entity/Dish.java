package by.bsuir.fooddelivio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private URL image;
    private String description;
    private double price;
    private int calories;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
