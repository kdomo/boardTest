package com.domo.boardtest.common;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResultDto<T> implements Serializable {
    private static final long serialVersionUID = -4549259724972937237L;
    private Integer code;
    private String msg;
    private T body;

    @Builder
    public ResultDto(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }
}