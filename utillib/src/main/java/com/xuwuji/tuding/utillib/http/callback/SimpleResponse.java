package com.xuwuji.tuding.utillib.http.callback;

import java.io.Serializable;

/**
 * `Author: Saint
 * Time:
 * ReadMe:
 */
public class SimpleResponse implements Serializable {
    public int status;
    public String message;

    public BaseResponse toBaseResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.status = status;
        baseResponse.message = message;
        return baseResponse;
    }
}
