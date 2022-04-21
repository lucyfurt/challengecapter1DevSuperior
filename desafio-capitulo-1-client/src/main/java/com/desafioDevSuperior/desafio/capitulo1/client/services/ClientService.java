package com.desafioDevSuperior.desafio.capitulo1.client.services;

import com.desafioDevSuperior.desafio.capitulo1.client.dto.ClientDTO;
import com.desafioDevSuperior.desafio.capitulo1.client.entities.Client;
import com.desafioDevSuperior.desafio.capitulo1.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));

    }
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        return new ClientDTO(entity);
    }
    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        //entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try{
            Client entity = repository.getOne(id);
            //entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + id);
        }

    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);

        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("ID NOT FOUND" +id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }
}
