package com.academy.certificate.controller;

import com.academy.certificate.dto.FamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.service.FamilyRelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/residents/{serialNumber}")
public class FamilyRelationshipController {
    FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    /*가족관계 등록*/
    @PostMapping("/relationship")
    public ResponseEntity<FamilyRelationship> doResisterFamilyRelationship(@PathVariable Long serialNumber,
                                             @Valid @RequestBody FamilyRelationshipDto dto,
                                                                           BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            throw new
//        }
        FamilyRelationship familyRelationship = familyRelationshipService.registerFamilyRelationship(serialNumber, dto);

        return ResponseEntity.ok(familyRelationship);
    }
}
