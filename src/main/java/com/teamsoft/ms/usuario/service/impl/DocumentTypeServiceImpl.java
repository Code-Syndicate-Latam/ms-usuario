package com.teamsoft.ms.usuario.service.impl;

import com.teamsoft.ms.usuario.entities.DocumentType;
import com.teamsoft.ms.usuario.repository.DocumentTypeRepository;
import com.teamsoft.ms.usuario.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public Optional<DocumentType> findById(Long id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    @Override
    public void deleteById(Long id) {
        documentTypeRepository.deleteById(id);
    }
}
