package dev.danielmesquita.dmclients.controllers;

import dev.danielmesquita.dmclients.dtos.ClientDTO;
import dev.danielmesquita.dmclients.services.ClientService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired private ClientService service;

  @GetMapping
  private ResponseEntity<Page<ClientDTO>> getAllClients(Pageable pageable) {
    Page<ClientDTO> clientDTOPage = service.findAll(pageable);
    return ResponseEntity.ok(clientDTOPage);
  }

  @GetMapping("/{id}")
  private ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
    ClientDTO clientDTO = service.findById(id);
    return ResponseEntity.ok(clientDTO);
  }

  @PostMapping
  private ResponseEntity<ClientDTO> insertClient(@Valid @RequestBody ClientDTO clientDTO) {
    clientDTO = service.insertClient(clientDTO);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(clientDTO.getId())
            .toUri();

    return ResponseEntity.created(uri).body(clientDTO);
  }

  @PutMapping("/{id}")
  private ResponseEntity<ClientDTO> update(
      @PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
    clientDTO = service.updateClient(id, clientDTO);
    return ResponseEntity.ok(clientDTO);
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<Void> delete(@PathVariable Long id) {
    service.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}
