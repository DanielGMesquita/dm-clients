package dev.danielmesquita.dmclients.repositories;

import dev.danielmesquita.dmclients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
