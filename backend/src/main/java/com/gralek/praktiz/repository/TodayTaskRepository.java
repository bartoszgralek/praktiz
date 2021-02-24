package com.gralek.praktiz.repository;

import com.gralek.praktiz.model.TodayTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TodayTaskRepository extends JpaRepository<TodayTask, Long> {

    Optional<TodayTask> findFirstByLocalDate(LocalDate date);

}
