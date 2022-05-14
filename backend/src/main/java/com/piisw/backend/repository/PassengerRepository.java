package com.piisw.backend.repository;

import com.piisw.backend.entity.user.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
