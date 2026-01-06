package com.teamsoft.ms.usuario.controller;

import com.teamsoft.ms.usuario.entities.UserDetail;
import com.teamsoft.ms.usuario.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user-details")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping
    public List<UserDetail> getAllUserDetails() {
        return userDetailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> getUserDetailById(@PathVariable Long id) {
        Optional<UserDetail> userDetail = userDetailService.findById(id);
        return userDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDetail> createUserDetail(@RequestBody UserDetail userDetail) {
        UserDetail savedUserDetail = userDetailService.save(userDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetail> updateUserDetail(@PathVariable Long id, @RequestBody UserDetail userDetailDetails) {
        Optional<UserDetail> optionalUserDetail = userDetailService.findById(id);
        if (optionalUserDetail.isPresent()) {
            UserDetail userDetail = optionalUserDetail.get();
            userDetail.setUserApp(userDetailDetails.getUserApp());
            userDetail.setDocumentType(userDetailDetails.getDocumentType());
            userDetail.setIdentificationNumber(userDetailDetails.getIdentificationNumber());
            userDetail.setDateOfBirth(userDetailDetails.getDateOfBirth());
            userDetail.setFirstName(userDetailDetails.getFirstName());
            userDetail.setMiddleName(userDetailDetails.getMiddleName());
            userDetail.setFirstLastName(userDetailDetails.getFirstLastName());
            userDetail.setSecondLastName(userDetailDetails.getSecondLastName());
            userDetail.setAddress(userDetailDetails.getAddress());
            return ResponseEntity.ok(userDetailService.save(userDetail));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetail(@PathVariable Long id) {
        if (userDetailService.findById(id).isPresent()) {
            userDetailService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
