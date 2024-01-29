package com.admirnurkovic.DatabaseConfig.mappers.impl;

import com.admirnurkovic.DatabaseConfig.domain.dto.AuthorDto;
import com.admirnurkovic.DatabaseConfig.domain.entities.AuthorEntity;
import com.admirnurkovic.DatabaseConfig.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

    private final ModelMapper modelMapper;


    public AuthorMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
