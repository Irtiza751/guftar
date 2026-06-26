package com.arbor.guftar.thread.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.arbor.guftar.thread.service.entity.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Long> {

    Page<Thread> findByUserId(Long userId, Pageable pageable);
}
