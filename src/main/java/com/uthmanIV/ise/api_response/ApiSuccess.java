package com.uthmanIV.ise.api_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiSuccess {

    private String message;
    private Object data;
}
