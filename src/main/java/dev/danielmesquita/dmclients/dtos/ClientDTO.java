package dev.danielmesquita.dmclients.dtos;

import dev.danielmesquita.dmclients.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class ClientDTO {

  private Long id;

  @NotBlank(message = "Name is required")
  @Size(min = 3, message = "Name must have at least 3 characters")
  private String name;

  @Pattern(regexp = "^[0-9]{11}$", message = "CPF must be 11 digits, without any special character")
  private String cpf;

  private Double income;

  @PastOrPresent(message = "Future birth date is not allowed")
  private LocalDate birthDate;

  private Integer children;

  public ClientDTO() {}

  public ClientDTO(
      Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.income = income;
    this.birthDate = birthDate;
    this.children = children;
  }

  public ClientDTO(Client client) {
    this.id = client.getId();
    this.name = client.getName();
    this.cpf = client.getCpf();
    this.income = client.getIncome();
    this.birthDate = client.getBirthDate();
    this.children = client.getChildren();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public Double getIncome() {
    return income;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public Integer getChildren() {
    return children;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    ClientDTO clientDTO = (ClientDTO) o;
    return Objects.equals(name, clientDTO.name) && Objects.equals(cpf, clientDTO.cpf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cpf);
  }
}
