package com.piisw.backend.data_initializer;

import com.github.javafaker.Faker;
import com.piisw.backend.entity.account_activation.AccountStatus;
import com.piisw.backend.entity.ticket.LongTermTicket;
import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket.SingleTicket;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.piisw.backend.entity.account_activation.AccountStatus.ACTIVE;
import static com.piisw.backend.entity.account_activation.AccountStatus.REGISTERED;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private static final String PASSWORD = "Test123!";

    private static final int ACTIVE_PASSENGER_AMOUNT = 9;
    private static final int REGISTERED_PASSENGER_AMOUNT = 3;

    private static final int SINGLE_TICKETS_AMOUNT = 1;
    private static final int SHORT_TERM_TICKETS_AMOUNT = 3;
    private static final int LONG_TERM_TICKETS_AMOUNT = 5;

    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;

    private final PasswordEncoder passwordEncoder;

    private Faker faker = new Faker();

    @Transactional
    @Override
    public void run(String... args) {
        initializeData();
    }

    private void initializeData() {
        initializePassengers();
        initializeTickets();
        log.info("Data initialized.");
    }

    private void initializePassengers() {
        List<Passenger> activePassengers = new ArrayList<>();
        List<Passenger> registeredPassengers = new ArrayList<>();

        IntStream.range(0, ACTIVE_PASSENGER_AMOUNT).forEach(i -> activePassengers.add(createPassenger(i, ACTIVE)));
        IntStream.range(0, REGISTERED_PASSENGER_AMOUNT).forEach(i -> registeredPassengers.add(createPassenger(i, REGISTERED)));

        passengerRepository.saveAll(Stream.of(activePassengers, registeredPassengers).flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private Passenger createPassenger(int number, AccountStatus accountStatus) {
        String password = null;

        if (ACTIVE.equals(accountStatus)) {
            password = passwordEncoder.encode(PASSWORD);
        }

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        if (number == 0 && accountStatus == ACTIVE) {
            firstName = "John";
            lastName = "Smith";
        }

        String mail = firstName.toLowerCase(Locale.ROOT) + "." + lastName.toLowerCase(Locale.ROOT) + "@mail.com";

        Passenger passenger = new Passenger(mail, firstName, lastName);
        passenger.setPassword(password);
        passenger.setAccountStatus(accountStatus);

        return passenger;
    }

    private void initializeTickets() {
        List<Ticket> singleTickets = new ArrayList<>();
        List<Ticket> shortTermTickets = new ArrayList<>();
        List<Ticket> longTermTickets = new ArrayList<>();

        IntStream.range(0, SINGLE_TICKETS_AMOUNT).forEach(i -> singleTickets.add(createSingleTicket(i)));
        IntStream.range(0, SHORT_TERM_TICKETS_AMOUNT).forEach(i -> shortTermTickets.add(createShortTermTicket(i)));
        IntStream.range(0, LONG_TERM_TICKETS_AMOUNT).forEach(i -> longTermTickets.add(createLongTermTicket(i)));

        ticketRepository.saveAll(Stream.of(singleTickets, shortTermTickets, longTermTickets)
                .flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private SingleTicket createSingleTicket(int number) {
        String name = "Single ticket " + number;
        BigDecimal price = getRandomTicketPrice();

        return new SingleTicket(price, price.divide(BigDecimal.valueOf(2.0)), name);
    }

    private ShortTermTicket createShortTermTicket(int number) {
        String name = "Short term ticket " + number;
        BigDecimal price = getRandomTicketPrice();

        int minutes = ThreadLocalRandom.current().nextInt(0, 30);
        int hours = ThreadLocalRandom.current().nextInt(0, 24);

        return new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), name, minutes, hours);
    }

    private LongTermTicket createLongTermTicket(int number) {
        String name = "Long term ticket " + number;
        BigDecimal price = getRandomTicketPrice();

        int days = ThreadLocalRandom.current().nextInt(0, 365);

        return new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), name, days);
    }

    private BigDecimal getRandomTicketPrice() {
        BigInteger bigInteger = BigInteger.probablePrime(6, new Random());
        return new BigDecimal(bigInteger);
    }
}
