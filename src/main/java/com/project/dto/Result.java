package com.project.dto;

import lombok.Data;


@Data
public class Result {
    private int code;
    private String msg;
    private Object content;

    public static Result ok() {
        Result result = new Result();
        result.setCode(200);
        return result;
    }

    public static Result ok(Object content) {
        Result result = new Result();
        result.setCode(200);
        result.setContent(content);
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static Result ok(Object content, String msg) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        result.setContent(content);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }
}
