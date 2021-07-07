package com.interview.test.service.impl;

import com.interview.test.dto.QuizApiResponse;
import com.interview.test.dto.QuizDto;
import com.interview.test.dto.mapper.DtoMapper;
import com.interview.test.model.Quiz;
import com.interview.test.service.QuizService;
import com.interview.test.util.Util;
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


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired(required=true)
    private  WebClient webClient;

    @Autowired(required=true)
    private  DtoMapper dtoMapper;

    @Autowired(required=true)
    private  Util util;

    public QuizServiceImpl(WebClient webClient, DtoMapper dtoMapper, Util util) {
        this.webClient = webClient;
        this.dtoMapper = dtoMapper;
        this.util = util;
    }

    @Override
        public Mono<Quiz> getQuiz(MultiValueMap<String, String> queryParams) {
            final String uri = util.buildURI(queryParams);
            return webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(Quiz.class)
                    .log();
        }

        @Override
        public Mono<QuizApiResponse> getQuizApiResponse(List<MultiValueMap<String, String>> queryParams) {

            Stream<Mono<QuizDto>> quizStream = queryParams.stream()
                    .map(this::getQuizDto);
            Mono<List<QuizDto>> mono = Flux.fromIterable(quizStream.collect(Collectors.toList()))
                    .flatMap(Function.identity())
                    .collectList();
            return mono.map(x -> {
                QuizApiResponse q = new QuizApiResponse();
                q.setQuiz(x);
                return q;
            });
        }

        @Override
        public Mono<QuizDto> getQuizDto(MultiValueMap<String, String> queryParams) {
            final Mono<Quiz> quiz = getQuiz(queryParams);
            return quiz.map(dtoMapper::quizToQuizDto);
        }


    }

