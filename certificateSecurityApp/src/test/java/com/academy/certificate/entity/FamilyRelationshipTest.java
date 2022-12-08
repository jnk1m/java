package com.academy.certificate.entity;

import com.academy.certificate.config.RootConfig;
import com.academy.certificate.config.WebConfig;
import com.academy.certificate.repository.FamilyRelationshipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class),
})
@Transactional
class FamilyRelationshipTest {
    @Autowired
    FamilyRelationshipRepository familyRelationshipRepository;


}