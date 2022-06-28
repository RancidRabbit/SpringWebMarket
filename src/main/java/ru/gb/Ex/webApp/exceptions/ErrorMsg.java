package ru.gb.Ex.webApp.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorMsg {

    private List<String> messages;

    private Date date;

    public ErrorMsg(List<String> messages) {
        this.messages = messages;
        this.date = new Date();
    }

    public ErrorMsg(String message) {
        this(List.of(message));
    }

    public ErrorMsg(String... messages) {
        this(Arrays.asList(messages));
    }
}
