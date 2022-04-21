package br.com.bank.relatorio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Sale {

    private Integer id;
    private List<ProductSale> productSaleList;
    private String salesMan;

    public Sale(String[] line){
        id = Integer.parseInt(line[1]);
        productSaleList = mapperProduct(line[2]);
        salesMan = line[3];
    }

    public Double totalSale() {
        return productSaleList.stream()
                .mapToDouble(v -> v.totalProduct().doubleValue())
                .sum();
    }

    private List<ProductSale> mapperProduct(String product) {
        List<ProductSale> products = new ArrayList<>();
        String[] lineProduct = product.replace("[", "").replace("]", "").split(",");

        Arrays.stream(lineProduct).forEach(
                productLine -> products.add(new ProductSale(id, productLine.split("-")))
        );
        return products;
    }

}

