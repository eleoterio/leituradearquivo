package br.com.bank.relatorio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class ProductSale {

    private Integer id;
    private Product product;
    private Integer quantity;

    public ProductSale(Integer sale, String[] lineProduct) {
        id = sale;
        product = new Product(lineProduct);
        quantity = Integer.parseInt(lineProduct[1]);
    }

    public BigDecimal totalProduct() {
        return BigDecimal.valueOf(product.getPrice().doubleValue() * quantity);
    }
}
