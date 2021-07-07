package com.interview.test.controller;

import com.interview.test.util.Util;
import com.interview.test.dto.QuizApiResponse;
import com.interview.test.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class QuizController {

    @Autowired(required=true)
    private  QuizService quizService;

    @Autowired
    private Util util;

    @RequestMapping(value = "/coding/exercise/quiz",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<QuizApiResponse>> getQuizzes() {

        Mono<QuizApiResponse> mono = quizService.getQuizApiResponse(util.generateQueryParams());

        return mono.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }




}
