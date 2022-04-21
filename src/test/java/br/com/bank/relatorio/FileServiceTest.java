package br.com.bank.relatorio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;

import br.com.bank.relatorio.entity.*;
import br.com.bank.relatorio.service.impl.FileServiceImpl;
import br.com.bank.relatorio.service.impl.StorageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class FileServiceTest {

    private FileServiceImpl fileService;
    private StorageServiceImpl storageService;
    private Storage storage;

    @BeforeEach
    void setup() {
        storageService = new StorageServiceImpl();
        fileService = new FileServiceImpl(storageService);
    }

    @Test
    void testVendor() {
        String[] line = {"001ç1234567891234çPedroç50000"};
        fileService.interpreter(Arrays.stream(line));

        Vendor vendor = storageService.getStorage().getVendorList().get(0);
        Assertions.assertEquals(new String("1234567891234"), vendor.getCpf());
        Assertions.assertEquals(new String("Pedro"), vendor.getName());
        Assertions.assertEquals(new Double(50000), vendor.getSalary().doubleValue());
    }

    @Test
    void testCostumerLineStorage() {
        String[] line = {"002ç2345675434544345çJose da SilvaçRural"};
        fileService.interpreter(Arrays.stream(line));

        Costumer costumer = storageService.getStorage().getCostumerList().get(0);
        Assertions.assertEquals(new String("2345675434544345"), costumer.getCnpj());
        Assertions.assertEquals(new String("Jose da Silva"), costumer.getName());
        Assertions.assertEquals(new String("Rural"), costumer.getBusiness());
    }

    @Test
    void testSaleLineStorage() {
        String[] line = {"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro"};
        fileService.interpreter(Arrays.stream(line));

        Sale sale = storageService.getStorage().getSaleList().get(0);
        ProductSale productSale = sale.getProductSaleList().get(0);
        Product product = productSale.getProduct();

        Assertions.assertEquals(new Integer(10), sale.getId());
        Assertions.assertEquals(new String("Pedro"), sale.getSalesMan());
        Assertions.assertEquals(new Integer(10), productSale.getId());
        Assertions.assertEquals(new Integer(10), productSale.getQuantity());
        Assertions.assertEquals(new Double(1000.0), productSale.totalProduct().doubleValue());
        Assertions.assertEquals(new Integer(1), product.getId());
        Assertions.assertEquals(new Double(100.0), product.getPrice().doubleValue());
    }

    @Test
    void testEntity() {
        assertThat("teste Entity Costumer", Costumer.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        assertThat("teste Entity Product", Product.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        assertThat("teste Entity ProductSale", ProductSale.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        assertThat("teste Entity Sale", Sale.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        assertThat("teste Entity Storage", Storage.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        assertThat("teste Entity Vendor", Vendor.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }
}
