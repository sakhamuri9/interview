package com.interview.test.dto.mapper;

import com.interview.test.dto.QuizDto;
import com.interview.test.dto.ResultDto;
import com.interview.test.model.Quiz;
import com.interview.test.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    @Mapping(target = "allAnswers", expression = "java(answersFromCorrectAndIncorrect(result))")
    ResultDto resultToResultDto(Result result);
    @Mappings({
            @Mapping(source = "results", target = "category", qualifiedByName = "categoryFromResults"),
            @Mapping(source = "results", target = "results")
    })
    QuizDto quizToQuizDto(Quiz quiz);

    @Named("categoryFromResults")
    default String categoryFromResults(List<Result> results) {
        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0).getCategory();
    }

    default List<String> answersFromCorrectAndIncorrect(Result result) {
        List<String> list = new ArrayList<>(result.getIncorrectAnswers());
        list.add(result.getCorrectAnswer());
        Collections.shuffle(list);
        return list;
    }
}
