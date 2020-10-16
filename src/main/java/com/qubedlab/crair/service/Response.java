package com.qubedlab.crair.service;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@JsonDeserialize
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Response<T> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "statusCode")
    private int statusCode;
    private boolean success;
    private String message;
    @JsonProperty(value = "responseBody")
    private T responseBody;

    public Response(int code, String message) {
        this.message = message;
        this.responseBody = null;
        this.success = code == 0 || code == 200;
        this.statusCode = code;
    }

    @JsonIgnore
    public Response<T> buildErrorResponse(String message) {
        this.message = message;
        this.success = false;
        this.responseBody = null;
        this.statusCode = 99;
        return this;
    }

    /**
     *
     * @param data
     * @return
     */
    @JsonIgnore
    public Response<T> buildSuccessResponse(final T data) {
        this.message = "Success";
        this.responseBody = data;
        this.success = true;
        this.statusCode = 0;
        return this;
    }

    /**
     *
     * @param message
     * @param data
     * @return
     */
    @JsonIgnore
    public Response<T> buildSuccessResponse(String message, final T data) {
        this.message = message;
        this.responseBody = data;
        this.success = true;
        this.statusCode = 0;
        return this;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Response<T> buildErrorResponse() {
        this.message = "fail";
        this.responseBody = null;
        this.success = false;
        this.statusCode = 99;
        return this;
    }

    /**
     *
     * @param code
     * @param message
     * @return
     */
    @JsonIgnore
    public Response<T> buildErrorResponse(int code, String message) {
        this.message = message;
        this.responseBody = null;
        this.success = false;
        this.statusCode = code;
        return this;
    }

    /**
     *
     * @param message
     * @param data
     * @return
     */
    @JsonIgnore
    public Response<T> buildErrorResponse(String message, final T data) {
        this.message = message;
        this.responseBody = data;
        this.success = true;
        this.statusCode = 0;
        return this;
    }

}

