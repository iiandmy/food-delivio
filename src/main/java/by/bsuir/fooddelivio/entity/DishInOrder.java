package by.bsuir.fooddelivio.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_dishes")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
