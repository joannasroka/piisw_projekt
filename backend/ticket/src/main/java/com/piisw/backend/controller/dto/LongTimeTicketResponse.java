package com.piisw.backend.controller.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LongTimeTicketResponse extends TicketResponse {

    private int days;
}
