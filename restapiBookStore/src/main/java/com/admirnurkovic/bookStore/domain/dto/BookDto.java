package com.admirnurkovic.bookStore.domain.dto;

import com.admirnurkovic.bookStore.validators.annotations.ValidAuthor;
import com.admirnurkovic.bookStore.validators.annotations.ValidBookIsbn;
import com.admirnurkovic.bookStore.validators.groups.BookPatchValidationGroup;
import com.admirnurkovic.bookStore.validators.groups.BookPostValidationGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    @ValidBookIsbn(groups = BookPostValidationGroup.class)
    private String isbn;
    @NotBlank(message = "Title is mandatory and cannot be blank", groups = {BookPostValidationGroup.class, BookPatchValidationGroup.class})
    private String title;
    @ValidAuthor(groups = BookPostValidationGroup.class)
    private AuthorDto authorEntity;
}
