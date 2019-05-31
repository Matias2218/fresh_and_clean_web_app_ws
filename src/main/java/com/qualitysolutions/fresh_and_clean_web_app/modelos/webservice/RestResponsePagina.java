package com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class RestResponsePagina<T> extends PageImpl<T> {

    private static final long serialVersionUID = 3248189030448292002L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestResponsePagina(@JsonProperty("content") List<T> content,
                              @JsonProperty("number") int number,
                              @JsonProperty("size") int size,
                              @JsonProperty("totalElements") Long totalElements,
                              @JsonProperty("pageable") JsonNode pageable,
                              @JsonProperty("last") boolean last,
                              @JsonProperty("totalPages") int totalPages,
                              @JsonProperty("sort") JsonNode sort,
                              @JsonProperty("first") boolean first,
                              @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }
    public RestResponsePagina(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestResponsePagina(List<T> content) {
        super(content);
    }

    public RestResponsePagina() {
        super(new ArrayList<T>());
    }
}
