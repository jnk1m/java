package com.academy.certificate.controller;

import com.academy.certificate.dto.FamilyRelationshipDto;
import com.academy.certificate.dto.ModifyFamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.service.FamilyRelationshipService;
import com.academy.certificate.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/residents/{serialNumber}")
public class FamilyRelationshipController {
    private final FamilyRelationshipService familyRelationshipService;
    private final ResidentService residentService;

    public FamilyRelationshipController(FamilyRelationshipService familyRelationshipService, ResidentService residentService) {
        this.familyRelationshipService = familyRelationshipService;
        this.residentService = residentService;
    }

    /*가족관계 등록*/
    @PostMapping("/relationship")
    public ResponseEntity<FamilyRelationship> doResisterFamilyRelationship(@PathVariable Long serialNumber,
                                                                           @Valid @RequestBody FamilyRelationshipDto dto,
                                                                           BindingResult bindingResult) throws ResidentNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("가족으로 등록할 주민의 일련 번호와 관계 코드를 모두 입력하세요.");
        }

        residentService.checkExistResident(serialNumber);

        FamilyRelationship familyRelationship = familyRelationshipService.registerFamilyRelationship(serialNumber, dto);

        return ResponseEntity.ok(familyRelationship);
    }

    @PutMapping("/relationship/{familySerialNumber}")
    public int doModifyFamilyRelationship(@PathVariable Long serialNumber,
                                          @PathVariable Long familySerialNumber,
                                          @Valid @RequestBody ModifyFamilyRelationshipDto dto,
                                          BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("가족 관계를 입력하세요");
        }

        return familyRelationshipService.modifyFamilyRelationship(serialNumber, familySerialNumber, dto);
    }


}
