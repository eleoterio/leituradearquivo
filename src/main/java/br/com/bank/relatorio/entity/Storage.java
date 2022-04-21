package br.com.bank.relatorio.entity;

import br.com.bank.relatorio.entity.Costumer;
import br.com.bank.relatorio.entity.Sale;
import br.com.bank.relatorio.entity.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Storage {

    private List<Vendor> vendorList;
    private List<Costumer> costumerList;
    private List<Sale> saleList;
    private Sale topSale;

}
