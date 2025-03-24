package com.test.test.repository;

import com.test.test.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rep extends JpaRepository<Task, Long> {}
