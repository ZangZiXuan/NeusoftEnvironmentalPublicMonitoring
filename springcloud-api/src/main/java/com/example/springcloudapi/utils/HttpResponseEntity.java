package com.example.springcloudapi.utils;

import lombok.Getter;

@Getter
public class HttpResponseEntity {
    private String code;     // status code
    private Object data;     // response data
    private String message;  // response message

    public HttpResponseEntity() {
    }

    /** Constructor*/
    public HttpResponseEntity(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * Error Response without response data
     */
    public static HttpResponseEntity error(String message) {
        return new HttpResponseEntity("0", null, message);
    }

    /** success Response with response data */
    public static HttpResponseEntity success(String message, Object data) {
        return new HttpResponseEntity("200", data, message);
    }

    /**success Response without response data*/
    public static HttpResponseEntity success(String message) {
        return new HttpResponseEntity("666", null, message);
    }

    /** create the response according to the flag to determine whether the operation is successful */
    public static HttpResponseEntity response(boolean flag, String message, Object data) {
        if (flag) {
            message = message + "successfully";
            return success(message, data);
        } else {
            message = "fail to "+ message;
            return error(message);
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private Object data;
        private String message;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        // 设置响应数据
        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        // 设置响应消息
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public HttpResponseEntity build() {
            return new HttpResponseEntity(code, data, message);
        }
    }
}
