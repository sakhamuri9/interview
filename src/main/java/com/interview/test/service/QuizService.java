package com.interview.test.service;

import com.interview.test.util.Util;
import com.interview.test.dto.QuizApiResponse;
import com.interview.test.dto.QuizDto;
import com.interview.test.dto.mapper.DtoMapper;
import com.interview.test.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface QuizService {
    Mono<Quiz> getQuiz(MultiValueMap<String, String> queryParams);

    Mono<QuizApiResponse> getQuizApiResponse();

    Mono<QuizDto> getQuizDto(MultiValueMap<String, String> queryParams);


}
