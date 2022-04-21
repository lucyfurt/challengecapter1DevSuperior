package com.desafioDevSuperior.desafio.capitulo1.client.repositories;

import com.desafioDevSuperior.desafio.capitulo1.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
