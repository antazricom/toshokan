package com.antazri.api.controller.response;

import java.util.Objects;

public class Response {

    private int code;
    private String message;
    private Object body;

    private Response() {
    }

    private Response(int code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response that = (Response) o;
        return getCode() == that.getCode() && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getBody());
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }

    public static class Builder {

        private int code;
        private String message;
        private Object body;

        public Builder(int code) {
            this.code = code;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder body(Object body) {
            this.body = body;
            return this;
        }

        public Response build() {
            return new Response(this.code, this.message, this.body);
        }
    }
}
