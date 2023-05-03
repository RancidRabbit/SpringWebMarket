package ru.gb.Ex.webApp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class  FileService {

    private final String baseDir;

    public FileService(@Value("${reportFilesDirectory}") String baseDir) throws IOException {
        this.baseDir = baseDir;
        Path path = Paths.get(baseDir);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

   public void save(String data, String fileName) throws IOException {
        Files.write(getFilePath(fileName), data.getBytes());
   }


   public Path getFilePath(String fileName) {
        return Paths.get(baseDir).resolve(fileName);
   }


   public byte[] getFileData(String fileName) throws IOException {
        /* Зачем тут resolve(fileName) ? */
        return Files.readAllBytes(getFilePath(fileName).resolve(fileName));
   }

}

