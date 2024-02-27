package com.erichiroshi.algafood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class StandardError {

    @CreationTimestamp
    private OffsetDateTime timestamp;
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String path;
    private String userMessage;

    private List<Object> objects;

    @Builder
    @Getter
    public static class Object {

        private String name;
        private String userMessage;
    }
}
