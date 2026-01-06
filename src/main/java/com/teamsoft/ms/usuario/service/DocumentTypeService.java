package com.teamsoft.ms.usuario.service;

import com.teamsoft.ms.usuario.entities.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {
    List<DocumentType> findAll();
    Optional<DocumentType> findById(Long id);
    DocumentType save(DocumentType documentType);
    void deleteById(Long id);
}
