package br.com.bank.relatorio.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Vendor {

    private String cpf;
    private String name;
    private BigDecimal salary;

    public Vendor(String[] line) {
        cpf = line[1];
        name = line[2];
        salary = BigDecimal.valueOf(Double.valueOf(line[3]));
    }

}
