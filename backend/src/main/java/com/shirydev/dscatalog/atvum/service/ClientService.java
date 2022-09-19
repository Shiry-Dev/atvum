package com.shirydev.dscatalog.atvum.service;

import com.shirydev.dscatalog.atvum.controller.handler.DataBaseException;
import com.shirydev.dscatalog.atvum.dto.ClientDTO;
import com.shirydev.dscatalog.atvum.entity.Client;
import com.shirydev.dscatalog.atvum.repository.ClientRepository;
import com.shirydev.dscatalog.atvum.service.exception.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
        Page<Client> list = repository.findAll(pageRequest);
        Page<ClientDTO> listDto = list.map(x -> new ClientDTO(x));
        return listDto;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> obj = repository.findById(id);
        Client entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entity not found"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new ClientDTO(entity);
        }catch(ControllerNotFoundException e){
            throw  new ControllerNotFoundException("ID not found" + id);
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ControllerNotFoundException("ID not found" + id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
