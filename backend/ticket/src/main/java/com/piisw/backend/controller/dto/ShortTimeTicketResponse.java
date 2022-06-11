package com.piisw.backend.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortTimeTicketResponse extends TicketResponse {

    private int minutes;

    private int hours;
}
