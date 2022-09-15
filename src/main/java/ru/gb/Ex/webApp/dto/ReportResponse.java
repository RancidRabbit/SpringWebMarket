package ru.gb.Ex.webApp.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportResponse {

    private String response;

    public ReportResponse(String response) {
        this.response = response;
    }
}
