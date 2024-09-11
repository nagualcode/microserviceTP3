package br.nagualcode.kitchenorders.domain;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;



public class KitchenOrder {

	@Id
    private Long id;
    private String product;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long customerId;

    // Construtor padrão
    public KitchenOrder() {}

    // Construtor com todos os campos
    public KitchenOrder(Long id, String product, Integer quantity, BigDecimal totalPrice, Long customerId) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
