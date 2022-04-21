package br.com.bank.relatorio.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Costumer {

    private String cnpj;
    private String name;
    private String business;

    public Costumer(String[] line){
        cnpj = line[1];
        name = line[2];
        business = line[3];
    }
}
