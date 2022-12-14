package com.academy.backed.server.backedserver.domain;

import com.academy.backed.server.backedserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
}
