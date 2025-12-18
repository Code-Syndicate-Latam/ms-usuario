package com.teamsoft.ms.usuario.controller;

import com.teamsoft.ms.usuario.entities.DocumentType;
import com.teamsoft.ms.usuario.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/document-types")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentType> getDocumentTypeById(@PathVariable Long id) {
        Optional<DocumentType> documentType = documentTypeService.findById(id);
        return documentType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DocumentType> createDocumentType(@RequestBody DocumentType documentType) {
        DocumentType savedDocumentType = documentTypeService.save(documentType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocumentType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentType> updateDocumentType(@PathVariable Long id, @RequestBody DocumentType documentTypeDetails) {
        Optional<DocumentType> optionalDocumentType = documentTypeService.findById(id);
        if (optionalDocumentType.isPresent()) {
            DocumentType documentType = optionalDocumentType.get();
            documentType.setDocumentName(documentTypeDetails.getDocumentName());
            documentType.setDescription(documentTypeDetails.getDescription());
            documentType.setStatus(documentTypeDetails.getStatus());
            return ResponseEntity.ok(documentTypeService.save(documentType));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
        if (documentTypeService.findById(id).isPresent()) {
            documentTypeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
