package ru.gb.Ex.webApp.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorMsg {

    private String message;

    private Date date;

    public ErrorMsg(String message) {
        this.message = message;
        this.date = new Date();
    }
}
