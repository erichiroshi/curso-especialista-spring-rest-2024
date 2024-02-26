package com.erichiroshi.algafood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class StandardError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String path;
    private String userMessage;

    private List<Field> fields;

    @Builder
    @Getter
    public static class Field {

        private String name;
        private String userMessage;
    }
}
