package br.com.bank.relatorio.service;

import br.com.bank.relatorio.entity.Costumer;
import br.com.bank.relatorio.entity.Sale;
import br.com.bank.relatorio.entity.Storage;
import br.com.bank.relatorio.entity.Vendor;

public interface StorageService {

    void addVendor(Vendor vendor);

    void addCostumer(Costumer costumer);

    void addSale(Sale sale);

    Integer totalCostumers();

    Integer totalVendors();

    String worstVendor();

    Integer topSale();
}
