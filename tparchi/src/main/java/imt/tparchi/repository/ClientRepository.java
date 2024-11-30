package imt.tparchi.repository;

import imt.tparchi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByName(String name);
}
