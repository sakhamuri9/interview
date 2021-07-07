package com.interview.test.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonRootName("quiz")
public class QuizApiResponse {
    List<QuizDto> quiz;
}
