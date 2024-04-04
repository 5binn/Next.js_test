package com.restapi.test.global.RsData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rsdata<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> Rsdata<T> of(String code, String msg, T data) {
        return new Rsdata<>(code, msg, data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return code.startsWith("S-");
    }
}
