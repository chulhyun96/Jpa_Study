package com.cheolhyeon.jpashop.domain.item;

import com.cheolhyeon.jpashop.domain.Category;
import com.cheolhyeon.jpashop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * 재고 증가
     */
    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }
    /**
     * 재고 감소
     */
    public void removeStockQuantity(int quantity) {
        if (this.stockQuantity - quantity < 0) {
            throw new NotEnoughStockException("need more stocks");
        }
        this.stockQuantity -= quantity;
    }
}
