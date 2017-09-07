package com.xier.sesame.attence.web.model;

import java.io.Serializable;

/**
 * Created by alpinist on 16-5-13.
 * 通知消息
 * {
 *     "objectId":*****,
 *     "operation":"****",
 *     "data":{
 *         "xxx":"yyy",
 *         "zzz":"hhh"
 *     }
 * }
 */
public class NotifyMessage<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7018113844617383302L;
	private Long objectId;
    private String operation;
    private T data;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
