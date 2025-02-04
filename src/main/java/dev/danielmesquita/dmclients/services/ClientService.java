package dev.danielmesquita.dmclients.services;

import dev.danielmesquita.dmclients.dtos.ClientDTO;
import dev.danielmesquita.dmclients.entities.Client;
import dev.danielmesquita.dmclients.repositories.ClientRepository;
import dev.danielmesquita.dmclients.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

  @Autowired private ClientRepository repository;

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAll(Pageable pageable) {
    Page<Client> clients = repository.findAll(pageable);
    return clients.map(ClientDTO::new);
  }

  public ClientDTO findById(Long id) {
    Client entity =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client doesn't exist"));
    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO insertClient(ClientDTO clientDTO) {
    Client entity = new Client();
    dtoToEntity(clientDTO, entity);

    entity = repository.save(entity);

    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
    try {
      Client entity = repository.getReferenceById(id);
      dtoToEntity(clientDTO, entity);

      entity = repository.save(entity);

      return new ClientDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Client doesn't exist");
    }
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void deleteClient(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Client doesn't exist");
    }
    repository.deleteById(id);
  }

  private void dtoToEntity(ClientDTO dto, Client entity) {
    entity.setName(dto.getName());
    entity.setCpf(dto.getCpf());
    entity.setBirthDate(dto.getBirthDate());
    entity.setIncome(dto.getIncome());
    entity.setChildren(dto.getChildren());
  }
}
