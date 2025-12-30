package com.example.tp_15.repositories;

import com.example.tp_15.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    // Sum all balances to support totalSolde GraphQL query
    @Query("SELECT COALESCE(SUM(c.solde), 0) FROM Compte c")
    double sumSoldes();

    // Optional: find by type if needed later
    @Query("SELECT c FROM Compte c WHERE c.type = :type")
    java.util.List<Compte> findByType(@Param("type") com.example.tp_15.entities.TypeCompte type);
}
