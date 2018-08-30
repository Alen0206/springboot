package com.tzc.springboot.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by zhangh on 16-7-14.
 * <p>
 * Author : zhangh
 *
 * @version : 1.0.0
 */
public class ResponseSerializer extends JsonSerializer<Response> {
    @Override
    public void serialize(Response response, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("success");
        jsonGenerator.writeBoolean(response.isSuccess());
        if (null != response.retCode) {
            jsonGenerator.writeFieldName("code");
            jsonGenerator.writeObject(response.retCode);
        }
        if (null != response.code && null != response.code.getMessage()) {
            jsonGenerator.writeFieldName("error");
            jsonGenerator.writeString(response.code.getMessage());
        }
        if (null != response.data) {
            jsonGenerator.writeFieldName("result");
            jsonGenerator.writeObject(response.data);
        }
        jsonGenerator.writeEndObject();
    }
}
