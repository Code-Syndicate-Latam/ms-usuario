package com.teamsoft.ms.usuario.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_app_generator")
    @SequenceGenerator(
            name = "user_app_generator",
            sequenceName = "user_app_user_app_id_seq", // Nombre exacto de la secuencia en PostgreSQL
            allocationSize = 1
    )
    @Column(name = "user_id")
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private Instant creationDate = Instant.now();

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DocumentType documentType;


    @Column(name = "identification_number", length = 20, nullable = false)
    private String identificationNumber;


    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;


    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;


    @Column(name = "middle_name", length = 50)
    private String middleName;


    @Column(name = "first_lastname", length = 50, nullable = false)
    private String firstLastName;


    @Column(name = "second_last_name", length = 50)
    private String secondLastName;


    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "phone", length = 50)
    private String phone;


}
