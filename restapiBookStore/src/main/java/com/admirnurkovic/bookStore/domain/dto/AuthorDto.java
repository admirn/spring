package com.admirnurkovic.bookStore.domain.dto;

import com.admirnurkovic.bookStore.validators.groups.AuthorPatchValidationGroup;
import com.admirnurkovic.bookStore.validators.groups.AuthorPostValidationGroup;
import com.admirnurkovic.bookStore.validators.annotations.ValidCountryCode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {
    private Long id;
    @NotBlank(message = "Name si mandatory", groups = {AuthorPostValidationGroup.class, AuthorPatchValidationGroup.class})
    private String name;
    @Min(value = 18, groups = {AuthorPostValidationGroup.class, AuthorPatchValidationGroup.class})
    private Integer age;
    @ValidCountryCode(groups = {AuthorPostValidationGroup.class, AuthorPatchValidationGroup.class})
    private String countryCode;
}
