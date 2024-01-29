package com.admirnurkovic.DatabaseConfig.mappers.impl;

import com.admirnurkovic.DatabaseConfig.domain.dto.BookDto;
import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;
import com.admirnurkovic.DatabaseConfig.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
