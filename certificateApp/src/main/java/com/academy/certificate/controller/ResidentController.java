package com.academy.certificate.controller;

import com.academy.certificate.dto.AliveResidentDto;
import com.academy.certificate.dto.EditResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    /*주민 등록*/
    @PostMapping
    public ResponseEntity<Resident> doResidentResist(@RequestBody AliveResidentDto aliveResidentDto) {
        Resident resident = residentService.registerResident(aliveResidentDto);
        return ResponseEntity.ok(resident);
    }

    /*주민 수정*/
    @PutMapping("/{serialNumber}")
    public void doModifyResident(@PathVariable Long serialNumber,
                                 @Valid @RequestBody EditResidentDto editResidentDto,
                                 BindingResult bindingResult) throws ResidentNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("주민등록번호 뒷자리 7글자만 수정 가능합니다.");
        }

        int i = residentService.modifyResidentInfo(serialNumber, editResidentDto);
    }
}
