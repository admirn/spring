package com.admirnurkovic.bookStore.validators.validators;

import com.admirnurkovic.bookStore.domain.dto.AuthorDto;
import com.admirnurkovic.bookStore.domain.entities.AuthorEntity;
import com.admirnurkovic.bookStore.mappers.Mapper;
import com.admirnurkovic.bookStore.services.AuthorService;
import com.admirnurkovic.bookStore.validators.annotations.ValidAuthor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class AuthorValidator implements ConstraintValidator<ValidAuthor, AuthorDto> {

    private final AuthorService authorService;
    private final Mapper<AuthorEntity, AuthorDto> authorMapper;
    @Override
    public boolean isValid(AuthorDto authorDto, ConstraintValidatorContext constraintValidatorContext) {

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        log.info("User exist in db: "+authorEntity.getId());
        return authorService.isExists(authorEntity.getId());

    }
}
