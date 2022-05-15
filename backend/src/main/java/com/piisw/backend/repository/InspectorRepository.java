package com.piisw.backend.repository;

import com.piisw.backend.entity.user.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectorRepository extends JpaRepository<Inspector, Long> {
}
