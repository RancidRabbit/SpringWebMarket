package ru.gb.Ex.webApp.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.gb.Ex.webApp.dto.AuthRequest;
import ru.gb.Ex.webApp.dto.AuthResponse;

@Controller
public class ReportController {

    @MessageMapping("/hello")
    /* После отправки сообщения клиента на сервер по эндпоинту /hello, отработает метод response
    * и отправит ответ всем подписчикам на топик (/topic/greetings). */
    @SendTo("/topic/greetings")
    public AuthResponse response(AuthRequest request) {
        return new AuthResponse("Token for " + HtmlUtils.htmlEscape(request.getUsername()));
    }


}
