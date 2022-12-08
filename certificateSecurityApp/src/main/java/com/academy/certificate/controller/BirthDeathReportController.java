package com.academy.certificate.controller;

import com.academy.certificate.domain.BirthReportDto;
import com.academy.certificate.domain.FamilyRelationshipDto;
import com.academy.certificate.domain.ResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.enums.FamilyRelationshipCode;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.service.BirthDeathReportService;
import com.academy.certificate.service.FamilyRelationshipService;
import com.academy.certificate.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/residents/{serialNumber}") //{serialNumber} : 행위자의 주민 일련번호
public class BirthDeathReportController {
    private final BirthDeathReportService birthDeathReportService;
    private  final ResidentService residentService;
    private final FamilyRelationshipService familyRelationshipService;

    public BirthDeathReportController(BirthDeathReportService birthDeathReportService, ResidentService residentService, FamilyRelationshipService familyRelationshipService) {
        this.birthDeathReportService = birthDeathReportService;
        this.residentService = residentService;
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping("/birth")
    public ResponseEntity<BirthReportDto> doBirthReport(@PathVariable Long serialNumber,
                                                       @Valid @RequestBody BirthReportDto dto,
                                                       BindingResult bindingResult) throws ResidentNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("출생 신고서의 모든 항목을 입력해주세요.");
        }

        residentService.checkExistResident(serialNumber);

        Resident resident = residentService.registerResident(new ResidentDto(dto.getName(),dto.getGenderCode(),
                dto.getBirthDate(), dto.getBirthPlaceCode(), dto.getRegistrationBaseAddress(), null, null, null));


        Long birthSerialNumber = resident.getResidentSerialNumber();
        BirthReportDto birthReport = birthDeathReportService.registerBirth(birthSerialNumber,serialNumber, dto);


        FamilyRelationshipDto mother = new FamilyRelationshipDto(birthSerialNumber, FamilyRelationshipCode.모);
        FamilyRelationshipDto father = new FamilyRelationshipDto(birthSerialNumber, FamilyRelationshipCode.부);
        familyRelationshipService.registerFamilyRelationship(dto.getMother(),mother);
        familyRelationshipService.registerFamilyRelationship(dto.getFather(),father);

        return ResponseEntity.ok(birthReport);
    }


}
