package com.example.tp_15;

import com.example.tp_15.entities.Compte;
import com.example.tp_15.entities.TypeCompte;
import com.example.tp_15.repositories.CompteRepository;
import com.example.tp_15.entities.Transaction;
import com.example.tp_15.entities.TypeTransaction;
import com.example.tp_15.repositories.TransactionRepository;
import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp15Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp15Application.class, args);
    }

    @Bean
    CommandLineRunner initData(CompteRepository compteRepository, TransactionRepository transactionRepository) {
        return args -> {
            if (compteRepository.count() == 0) {
                // Seed a few comptes at startup
                Compte c1 = compteRepository.save(new Compte(null, 1200.0, new Date(), TypeCompte.COURANT));
                Compte c2 = compteRepository.save(new Compte(null, 5000.0, new Date(), TypeCompte.EPARGNE));
                Compte c3 = compteRepository.save(new Compte(null, 250.5, new Date(), TypeCompte.COURANT));

                // Seed some transactions
                transactionRepository.save(new Transaction(null, 200.0, new Date(), TypeTransaction.DEPOT, c1));
                transactionRepository.save(new Transaction(null, 50.0, new Date(), TypeTransaction.RETRAIT, c1));
                transactionRepository.save(new Transaction(null, 400.0, new Date(), TypeTransaction.DEPOT, c2));
            }
        };
    }
}
