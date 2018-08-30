package com.tzc.springboot.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

/**
 * Author      : zhangh
 * Create Time : 2015/12/15 下午 04:40
 * Project     : commons-parent
 * Version     : 1.0.0
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@JsonSerialize(using = ResponseSerializer.class)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1506433266912481316L;

    public static final String SUCCESS_CODE = "200";
    public static final String DEFAULT_FAIL_CODE = "500";
    public static final String PERMISSION_DENIED = "401";
    public static final String ILLEGAL_PARAM = "400";
    public static final String SUCCESS_MESSAGE = "处理成功";

    Code code = Code.SUCCESS;
    T data;
    String retCode;

    public Response<T> setData(Expect expect, Code pass, Code fail, T data, String retCode) {
        return setData(expect, pass, fail, data, new Object(), retCode);
    }

    public Response<T> setData(Expect expect, Code pass, Code fail, T data, Object o, String retCode) {
        this.data = data;
        this.code = fail;
        this.retCode = retCode;

        if (null == this.data) return this;

        switch (expect) {
            case NOTNULL: {
                this.code = pass;
                break;
            }
            case NOTEMPTY: {
                if (!(this.data instanceof Collection)) break;
                if (((Collection) this.data).isEmpty()) break;
                this.code = pass;
                break;
            }
            case TRUE: {
                if (this.data instanceof Boolean) {
                    if ((Boolean) this.data) this.code = pass;
                    break;
                } else if (this.data instanceof IExpect) {
                    if (((IExpect) this.data).validate()) this.code = pass;
                    break;
                }
                break;
            }
            case FALSE: {
                if (this.data instanceof Boolean) {
                    if (!(Boolean) this.data) this.code = pass;
                    break;
                } else if (this.data instanceof IExpect) {
                    if (!((IExpect) this.data).validate()) this.code = pass;
                    break;
                }
                break;
            }
            case EQ: {
                if (data.equals(o)) {
                    this.code = pass;
                }
                break;
            }
            case NE: {
                if (!data.equals(o)) {
                    this.code = pass;
                }
                break;
            }
        }
        return this;
    }

    public Response() {
    }

    public Response(Code code) {
        this.code = new Code(code);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return null != this.code && this.code.isSuccess();
    }

    public static <T> Response<T> ok(T data) {
        Response<T> resp = new Response<>(new Code());
        resp.data = data;
        resp.setRetCode(SUCCESS_CODE);
        return resp;
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> fail(String error, String retCode) {
        Response resp = new Response<>(new Code(0, 0, 0, -1));
        resp.setRetCode(retCode);
        resp.setData(Boolean.FALSE);
        resp.code.setMessage(error);
        return resp;
    }

    public static <T> Response<T> fail(String error) {
        return fail(error, DEFAULT_FAIL_CODE);
    }

    public Response<T> setResult(T result) {
        this.code = new Code();
        this.data = result;
        this.setRetCode(SUCCESS_CODE);
        return this;
    }

    @JsonIgnore
    public T getResult() {
        return this.data;
    }

    @JsonIgnore
    public String getError() {
        return this.code.getMessage();
    }

    public Response<T> setError(String error, String retCode) {
        this.code = Code.FAILURE;
        this.retCode = retCode;
        this.code.setMessage(error);
        return this;
    }

    public Response<T> setSuccess(boolean success) {
        if (success)
            this.setCode(new Code(0, 0, 0, 0));
        else
            this.setCode(new Code(0, 0, 0, -1));
        return this;
    }

    public enum DataType {
        STRUCT("{", "}"), PRIMITIVE(null, null), ARRAY("[", "]");
        public final String start;
        public final String end;

        DataType(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    public StringBuffer startInJson(final StringBuffer sb, DataType type) {
        sb.append("{").append("\"code\":\"").append(Long.toHexString(code.longValue()))
                .append("\",\"message\":\"").append(code.getMessage()).append("\"")
                .append(",\"data\":");
        switch (type) {
            case STRUCT:
                return sb.append(DataType.STRUCT.start);
            case ARRAY:
                return sb.append(DataType.ARRAY.start);
            case PRIMITIVE:
            default:
                return sb;
        }
    }

    public StringBuffer endInJson(final StringBuffer sb, DataType type) {
        switch (type) {
            case STRUCT:
                return sb.append(DataType.STRUCT.end).append("}");
            case ARRAY:
                return sb.append(DataType.ARRAY.end).append("}");
            case PRIMITIVE:
            default:
                return sb.append("}");
        }
    }
}
