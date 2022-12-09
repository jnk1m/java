package com.academy.certificate.repository;

import com.academy.certificate.domain.ToBeResidentList;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    List<ToBeResidentList> getCompositionResidentList(Long residentSerialNumber);
}
