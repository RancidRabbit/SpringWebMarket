package ru.gb.Ex.webApp.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.gb.Ex.webApp.dto.AuthRequest;
import ru.gb.Ex.webApp.dto.ReportRequest;
import ru.gb.Ex.webApp.dto.ReportResponse;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.services.FileService;
import ru.gb.Ex.webApp.services.ProductExcelExporter;
import ru.gb.Ex.webApp.services.ProductService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReportController {

    private final ProductService productService;

    private final FileService fileService;

    @MessageMapping("/hello")
    /* После отправки сообщения клиента на сервер по эндпоинту /hello,(app/hello!) отработает метод response
    * и отправит ответ всем подписчикам, подписанным на топик (/topic/greetings). */
    @SendTo("/topic/greetings")
    /* принимаем в параметры то, что пришло от sendMsg(JS) (шаг 2)*/
    public String response(ReportRequest request) throws IOException {

        List<Product> productList = productService.findAll();

        ProductExcelExporter exporter = new ProductExcelExporter(productList);

        exporter.writeHeaderLine();
        exporter.writeDataLines();

        try(FileOutputStream fos = new FileOutputStream(fileService.getFilePath("report.xlsx").toFile())) {
            exporter.getWorkbook().write(fos);
        }

       request.setLink(fileService.getFilePath("report.xlsx").toFile().getAbsolutePath());

        return "Отчет скачан [" + HtmlUtils.htmlEscape(request.getLink()) + "]";
    }


}
