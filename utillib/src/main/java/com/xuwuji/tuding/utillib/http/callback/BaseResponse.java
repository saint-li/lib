package com.xuwuji.tuding.utillib.http.callback;

import java.io.Serializable;

/**
 * `Author: Saint
 * Time:
 * ReadMe:
 */
public class BaseResponse<T> implements Serializable {


    public int status;
    public String message;
    public T data;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
