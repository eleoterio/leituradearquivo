package br.com.bank.relatorio.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Integer id;
    private BigDecimal price;

    public Product(String[] line) {
        id = Integer.parseInt(line[0]);
        price = BigDecimal.valueOf(Double.valueOf(line[2]));
    }
}
