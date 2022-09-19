package com.shirydev.dscatalog.atvum.repository;

import com.shirydev.dscatalog.atvum.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
