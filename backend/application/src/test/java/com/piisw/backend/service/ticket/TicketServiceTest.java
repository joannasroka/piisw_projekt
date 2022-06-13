package com.piisw.backend.service.ticket;

import com.piisw.backend.controller.dto.ticket.ShortTimeTicketResponse;
import com.piisw.backend.controller.dto.ticket.TicketResponse;
import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket.TicketType;
import com.piisw.backend.mapper.TicketMapper;
import com.piisw.backend.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class TicketServiceTest {

    @InjectMocks
    TicketService ticketService;

    @Mock
    TicketMapper ticketMapper;

    @Mock
    TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get ticket by Id")
    void shouldGetTicketById() {
        //given
        BigDecimal price = BigDecimal.valueOf(3.2);
        ShortTermTicket ticket = new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "15-minute ticket", 15, 0);

        ShortTimeTicketResponse shortTimeTicketResponse = new ShortTimeTicketResponse();
        shortTimeTicketResponse.setTicketType(TicketType.SHORT_TERM);
        shortTimeTicketResponse.setHours(0);
        shortTimeTicketResponse.setMinutes(15);
        shortTimeTicketResponse.setName("15-minute ticket");
        shortTimeTicketResponse.setNormalPrice(price);
        shortTimeTicketResponse.setReducedPrice(price.divide(BigDecimal.valueOf(2.0)));

        Long ticketId = 1L;

        when(ticketRepository.getById(ticketId)).thenReturn(ticket);
        when(ticketMapper.mapToTicketResponse(ticket)).thenReturn(shortTimeTicketResponse);

        //when
        TicketResponse actualTicket = ticketService.getById(ticketId);

        //then
        assertNotNull(actualTicket);
        assertEquals(shortTimeTicketResponse, actualTicket);

        verify(ticketRepository, times(1)).getById(1L);
    }
}
