package com.academy.certificate.controller;

import com.academy.certificate.domain.ModifyResidentDto;
import com.academy.certificate.domain.ResidentDto;
import com.academy.certificate.domain.ToBeResidentList;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.service.ResidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    /*주민 등록*/
    @PostMapping
    public ResponseEntity<Resident> doResidentResist(@RequestBody ResidentDto residentDto) {
        Resident resident = residentService.registerResident(residentDto);
        return ResponseEntity.ok(resident);
    }

    /*주민 수정*/
    @PutMapping("/{serialNumber}")
    public int doModifyResident(@PathVariable Long serialNumber,
                                @Valid @RequestBody ModifyResidentDto modifyResidentDto,
                                BindingResult bindingResult) throws ResidentNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("주민등록번호 뒷자리 7글자만 수정 가능합니다.");
        }


        return residentService.modifyResidentInfo(serialNumber, modifyResidentDto);
    }

    /*주민 목록*/

    @GetMapping()
    public ModelAndView getResidentsList() {
        String userId = residentService.getUserIdFromSecurityContextHolder();

        Resident resident = residentService.getResidentByUserId(userId);

        Long householdSerialNumber = residentService.getHouseholdSerialNumber(resident.getResidentSerialNumber());
        System.out.println("householdSerialNumber = " + householdSerialNumber);

        List<ToBeResidentList> residents = residentService.getHouseholdCompositionResidents(householdSerialNumber);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("residents");
        modelAndView.addObject("residents", residents);

        return modelAndView;
    }


}
