package com.teamsoft.ms.usuario.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @JsonProperty("document_type_id")
    @NotNull(message = "document_type_id es obligatorio")
    private Long documentTypeId;

    @JsonProperty("identification_number")
    @NotBlank(message = "identification_number es obligatorio")
    @Size(max = 20, message = "identification_number máximo 20 caracteres")
    private String identificationNumber;

    @JsonProperty("date_of_birth")
    @NotNull(message = "date_of_birth es obligatorio")
    private LocalDate dateOfBirth;

    @JsonProperty("first_name")
    @NotBlank(message = "first_name es obligatorio")
    @Size(max = 50, message = "first_name máximo 50 caracteres")
    private String firstName;

    @JsonProperty("middle_name")
    @Size(max = 50, message = "middle_name máximo 50 caracteres")
    private String middleName;

    @JsonProperty("first_last_name")
    @NotBlank(message = "first_last_name es obligatorio")
    @Size(max = 50, message = "first_last_name máximo 50 caracteres")
    private String firstLastName;

    @JsonProperty("second_last_name")
    @Size(max = 50, message = "second_last_name máximo 50 caracteres")
    private String secondLastName;

    @JsonProperty("address")
    @Size(max = 250, message = "address máximo 250 caracteres")
    private String address;

    @JsonProperty("phone")
    @Size(max = 50, message = "phone máximo 50 caracteres")
    private String phone;
}
