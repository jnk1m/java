package com.academy.certificate.controller;

import com.academy.certificate.dto.AliveResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    ResidentRepository residentRepository;

    public ResidentController(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @PostMapping
    public ModelAndView doResidentResist(@Valid @ModelAttribute AliveResidentDto aliveResidentDto,
                                         BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Resident resident = new Resident(aliveResidentDto.getResidentSerialNumber(),
                aliveResidentDto.getName(), aliveResidentDto.getResidentRegistrationNumber(),
                aliveResidentDto.getGenderCode(), aliveResidentDto.getBirthDate(),
                aliveResidentDto.getBirthPlaceCode(), aliveResidentDto.getRegistrationBaseAddress());

        residentRepository.saveAndFlush(resident);

        modelAndView.setViewName("result");
        modelAndView.addObject("response",resident);


        return modelAndView;
    }
}
