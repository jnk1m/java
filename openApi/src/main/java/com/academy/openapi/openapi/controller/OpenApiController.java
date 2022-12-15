package com.academy.openapi.openapi.controller;

import com.academy.openapi.openapi.domain.AccountCreateRequest;
import com.academy.openapi.openapi.domain.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class OpenApiController {
    private final RestTemplate restTemplate;
    private UriComponents uriComponents;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(9090)
                .path("/accounts")
                .build();

        ResponseEntity<AccountDto[]> responseEntity = restTemplate.getForEntity(uriComponents.toUri(), AccountDto[].class);
        List<AccountDto> accountDtoList = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));

        return ResponseEntity.ok(accountDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(9090)
                .path("/accounts/" + id)
                .build();

        AccountDto accountDto = restTemplate.getForObject(uriComponents.toUri(), AccountDto.class);
        return ResponseEntity.ok(accountDto);

    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = "validation error." +
                    " field: " + fieldError.getField() +
                    ", code: " + Arrays.toString(fieldError.getCodes()) +
                    ", message : " + fieldError.getDefaultMessage();

            System.out.println(errorMessage);
            return ResponseEntity.badRequest().build();
        }
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(9090)
                .path("/accounts")
                .build();

        AccountDto accountDto = restTemplate.postForObject(uriComponents.toUri(), accountCreateRequest, AccountDto.class);
        return ResponseEntity.ok(accountDto);

    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(9090)
                .path("/accounts/" + id)
                .build();

        restTemplate.delete(uriComponents.toUri());

    }
}
