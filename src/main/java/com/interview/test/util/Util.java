package com.interview.test.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Util {

    @Value("${quiz.amount}")
    private String amount;
    @Value("${quiz.categories}")
    private List<String> categories;
    @Value("${quiz.endpoint}")
    public String endpoint;

    public List<MultiValueMap<String, String>> generateQueryParams(){
        Stream<MultiValueMapAdapter<String, String>> multiValueMapAdapterStream = categories.stream()
                .map(x -> {
                    Map<String, List<String>> map = new HashMap<>();
                    map.put("amount", Collections.singletonList(amount));
                    map.put("category", Collections.singletonList(x));
                    return new MultiValueMapAdapter<>(map);
                });
       return multiValueMapAdapterStream.collect(Collectors.toList());
    }

    public String buildURI(MultiValueMap<String, String> queryParams) {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endpoint);
        return uriComponentsBuilder.queryParams(queryParams)
                .build()
                .toUri()
                .toString();
    }
}
