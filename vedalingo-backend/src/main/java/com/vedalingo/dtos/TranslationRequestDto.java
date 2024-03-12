package com.vedalingo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequestDto {
    //    private String prompt;
    private String text;
    private String targetLanguage;

}
