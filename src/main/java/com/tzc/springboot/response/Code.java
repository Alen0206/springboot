package com.tzc.springboot.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Code implements Serializable {
    public final static Code SUCCESS = new Code(0, 0, 0, 0);
    public final static Code FAILURE = new Code(0, 0, 0, -1);

    /**
     * Error module.
     */
    public Integer mid;
    /**
     * Sub middle id.
     */
    public Integer smid;
    /**
     * Error sequence.
     */
    public Integer seq;
    /**
     * Error level.
     */
    public Integer level;
    /**
     * Error detail information.
     */
    public String message;
    /**
     * Logs usage.
     */
    public String pattern = "";

    public Code() {
        this(0, 0, 0, 0);
    }

    public Code(Integer level, Integer mid, Integer smid, Integer seq) {
        this.level = level;
        this.mid = mid;
        this.smid = smid;
        this.seq = seq;
    }

    public Code(Code code) {
        this.level = code.level;
        this.mid = code.mid;
        this.smid = code.smid;
        this.seq = code.seq;
        this.message = code.message;
        this.pattern = code.pattern;
    }

    public long longValue() {
        return (((long) level & 0xff) << 56) |
                (((long) mid & 0xffff) << 40) |
                (((long) smid & 0xff) << 32) |
                (seq);
    }

    public Code dup(String message) {
        Code c = new Code(this);
        c.setMessage(message);
        return c;
    }

    /**
     * Indicate success or not.
     *
     * @return true if success, false if failure.
     */
    public boolean isSuccess() {
        return level == 0 && mid == 0 && smid == 0 && seq == 0;
    }

}
