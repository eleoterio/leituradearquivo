package br.com.bank.relatorio.service.impl;

import br.com.bank.relatorio.entity.Costumer;
import br.com.bank.relatorio.entity.Sale;
import br.com.bank.relatorio.entity.Storage;
import br.com.bank.relatorio.entity.Vendor;
import br.com.bank.relatorio.service.StorageService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class StorageServiceImpl implements StorageService {

    private Storage storage = new Storage();

    public StorageServiceImpl() {
        storage.setCostumerList(new ArrayList<Costumer>());
        storage.setVendorList(new ArrayList<Vendor>());
        storage.setSaleList(new ArrayList<Sale>());
    }

    @Override
    public void addVendor(Vendor vendor) {
        storage.getVendorList().add(vendor);
    }

    @Override
    public void addCostumer(Costumer costumer) {
        storage.getCostumerList().add(costumer);
    }

    @Override
    public void addSale(Sale sale) {
        storage.getSaleList().add(sale);
        checkSale(sale);
    }

    @Override
    public Integer totalCostumers() {
        return storage.getCostumerList().size();
    }

    @Override
    public Integer totalVendors() {
        return storage.getVendorList().size();
    }

    @Override
    public String worstVendor() {
        return storage.getSaleList().stream()
                .collect(Collectors.groupingBy(Sale::getSalesMan,
                        Collectors.summingDouble(Sale::totalSale)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList())
                .get(0).getKey();
    }

    @Override
    public Integer topSale(){
        return storage.getTopSale().getId();
    }

    private void checkSale(Sale sale) {
        if (storage.getTopSale() == null || storage.getTopSale().totalSale() < sale.totalSale())
            storage.setTopSale(sale);
    }
}
