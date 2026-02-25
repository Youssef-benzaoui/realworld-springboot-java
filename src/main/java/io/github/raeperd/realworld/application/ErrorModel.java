package io.github.raeperd.realworld.application;

import lombok.Value;

import java.util.List;

@Value
class ErrorModel {

    Errors errors;

    static ErrorModel of(List<String> messages) {
        return new ErrorModel(new Errors(messages));
    }

    @Value
    static class Errors {
        List<String> body;
    }
}
