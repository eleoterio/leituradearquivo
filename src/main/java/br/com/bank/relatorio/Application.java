package br.com.bank.relatorio;

import br.com.bank.relatorio.service.FileService;
import br.com.bank.relatorio.service.StorageService;
import br.com.bank.relatorio.service.impl.FileServiceImpl;
import br.com.bank.relatorio.service.impl.StorageServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Application {


    private static FileService fileService;

    public static void main(String[] args) throws IOException, InterruptedException {
        fileService = new FileServiceImpl(new StorageServiceImpl());
        Properties properties = initProperties();

        while (true){
            fileService.searchFile(properties).stream()
                    .forEach(file -> fileService.readFile(file, properties));
            Thread.sleep(3000);
        }
    }

    public static Properties initProperties() {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "application.properties";

            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            return appProps;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
