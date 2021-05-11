package com.qixinmini.common.core.model;

import java.util.HashMap;

/**
 *  @Description 响应封装
 *  @author lijie
 *  @Date 2020/11/13 9:20
 */
public class MicroserviceResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public MicroserviceResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public MicroserviceResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public MicroserviceResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
