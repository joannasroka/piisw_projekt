package com.piisw.backend.data_initializer;

import com.github.javafaker.Faker;
import com.piisw.backend.entity.account_activation.AccountStatus;
import com.piisw.backend.entity.ticket.LongTermTicket;
import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket.SingleTicket;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.entity.user.Inspector;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.repository.InspectorRepository;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
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

    private static final int INSPECTOR_AMOUNT = 3;

    private final PassengerRepository passengerRepository;
    private final InspectorRepository inspectorRepository;
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
        initializeInspectors();
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

    private void initializeInspectors() {
        List<Inspector> inspectors = new ArrayList<>();

        IntStream.range(0, INSPECTOR_AMOUNT).forEach(i -> inspectors.add(createInspector(i)));

        inspectorRepository.saveAll(Stream.of(inspectors).flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private Inspector createInspector(int number) {
        String password = passwordEncoder.encode(PASSWORD);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        if (number == 0) {
            firstName = "Maria";
            lastName = "Smith";
        }

        String mail = firstName.toLowerCase(Locale.ROOT) + "." + lastName.toLowerCase(Locale.ROOT) + "@mail.com";

        Inspector inspector = new Inspector(mail, firstName, lastName);
        inspector.setPassword(password);
        inspector.setAccountStatus(ACTIVE);

        return inspector;
    }

    private void initializeTickets() {
        List<Ticket> singleTickets = new ArrayList<>();
        List<Ticket> shortTermTickets = new ArrayList<>();
        List<Ticket> longTermTickets = new ArrayList<>();

        BigDecimal price = BigDecimal.valueOf(4.6);
        singleTickets.add(new SingleTicket(price, price.divide(BigDecimal.valueOf(2.0)), "Single ticket"));

        price = BigDecimal.valueOf(3.2);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "15-minute ticket", 15, 0));
        price = BigDecimal.valueOf(4.0);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "30-minute ticket", 30, 0));
        price = BigDecimal.valueOf(5.2);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "60-minute ticket", 60, 0));
        price = BigDecimal.valueOf(7.0);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "90-minute ticket", 90, 0));
        price = BigDecimal.valueOf(15.0);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "24-hour ticket", 0, 24));
        price = BigDecimal.valueOf(26.0);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "48-hour ticket", 0, 48));
        price = BigDecimal.valueOf(32.0);
        shortTermTickets.add(new ShortTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "72-hour ticket", 0, 72));

        price = BigDecimal.valueOf(38.0);
        longTermTickets.add(new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "7-day ticket", 7));
        price = BigDecimal.valueOf(110.0);
        longTermTickets.add(new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "30-day ticket", 30));
        price = BigDecimal.valueOf(208.0);
        longTermTickets.add(new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "60-day ticket", 60));
        price = BigDecimal.valueOf(208.0);
        longTermTickets.add(new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "90-day ticket", 90));
        price = BigDecimal.valueOf(560.0);
        longTermTickets.add(new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "180-day ticket", 180));
        price = BigDecimal.valueOf(1050.0);
        longTermTickets.add(new LongTermTicket(price, price.divide(BigDecimal.valueOf(2.0)), "365-day ticket", 365));


        ticketRepository.saveAll(Stream.of(singleTickets, shortTermTickets, longTermTickets)
                .flatMap(Collection::stream).collect(Collectors.toList()));
    }
}
