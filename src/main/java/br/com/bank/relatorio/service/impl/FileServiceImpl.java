package br.com.bank.relatorio.service.impl;

import br.com.bank.relatorio.entity.Costumer;
import br.com.bank.relatorio.entity.Sale;
import br.com.bank.relatorio.entity.Storage;
import br.com.bank.relatorio.entity.Vendor;
import br.com.bank.relatorio.service.FileService;
import br.com.bank.relatorio.service.StorageService;
import org.apache.commons.io.FilenameUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileServiceImpl  implements FileService {

    public static StorageService storageService;

    public FileServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public List<Path> searchFile(Properties properties) throws IOException {
        return Files.list(Paths.get(properties.getProperty("inFile")))
                .filter(path -> path.toFile().isFile())
                .filter(path -> path.toFile().getName().contains(".dat"))
                .collect(Collectors.toList());
    }

    @Override
    public void generateFile(Path file, String outputFilePath) {
        try {
            String outputFileName = String.format(outputFilePath,
                    FilenameUtils.getBaseName(file.toString()));

            FileWriter fileWriter = new FileWriter(outputFileName);
            fileWriter.write(line("Quantidade de clientes no arquivo de entrada: " + storageService.totalCostumers()));
            fileWriter.write(line("Quantidade de vendedor no arquivo de entrada: " + storageService.totalVendors()));
            fileWriter.write(line("ID da venda mais cara: " + storageService.topSale()));
            fileWriter.write(line("O pior vendedor: " + storageService.worstVendor()));

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while building output file - " + file);
            e.printStackTrace();
        }
    }
    @Override
    public void readFile(Path file, Properties properties) {
        try {
            Stream<String> stream = Files.lines(file);

            interpreter(stream);

            generateFile(file, properties.getProperty("outFile"));
            cleanStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interpreter(Stream<String> stream){
        stream.map(line -> line.split("ç"))
                .collect(Collectors.toList())
                .forEach(line -> {
                    identify(line);
                });
    }

    private String line(String msg){
        return new String(msg + System.lineSeparator());
    }

    private void identify(String[] lineData){
        String identifier = lineData[0];
        switch (identifier) {
            case "001":
                storageService.addVendor(new Vendor(lineData));
                break;
            case "002":
                storageService.addCostumer(new Costumer(lineData));
                break;
            case "003":
                storageService.addSale(new Sale(lineData));
                break;
        }
    }

    private void cleanStorage() {
        storageService = new StorageServiceImpl();
    }

}
