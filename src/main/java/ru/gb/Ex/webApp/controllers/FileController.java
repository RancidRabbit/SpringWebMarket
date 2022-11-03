package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.dto.FileSaveRequest;
import ru.gb.Ex.webApp.services.FileService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


    @PostMapping
    public Boolean saveString(@RequestBody FileSaveRequest fileSaveRequest) {
        try {
           fileService.save(fileSaveRequest.getText(), fileSaveRequest.getName());
           return true;
        } catch (IOException e) {
            return false;
        }
    }
    @GetMapping(value = "/{fileName}", produces = "application/octet-stream")
    public byte[] getFile(@PathVariable String fileName) {
        try {
            return fileService.getFileData(fileName);
        } catch (IOException e) {
            return new byte[]{};
        }
    }

}
