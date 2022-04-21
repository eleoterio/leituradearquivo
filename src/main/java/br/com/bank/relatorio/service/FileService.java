package br.com.bank.relatorio.service;

import br.com.bank.relatorio.entity.Storage;
import lombok.Value;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public interface FileService {

    List<Path> searchFile(Properties properties) throws IOException;

    void generateFile(Path file, String outputFilePath);

    void readFile(Path file, Properties properties);

    void interpreter(Stream<String> stream);
}
