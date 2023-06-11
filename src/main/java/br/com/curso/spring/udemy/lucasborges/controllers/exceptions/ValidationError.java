package br.com.curso.spring.udemy.lucasborges.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("Errors")
    private List<FieldMessage> fieldMessageList = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String detailMessage, String path) {
        super(timestamp, status, error,detailMessage,path);

    }
    public void addError(String fieldName, String message) {
        fieldMessageList.add(new FieldMessage(fieldName, message));
    }

}
