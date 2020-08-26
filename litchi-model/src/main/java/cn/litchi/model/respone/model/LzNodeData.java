package cn.litchi.model.respone.model;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class LzNodeData {
    private Instant time;
    private String data;
    private int line;

    /**
     * 因为是重构，旧代码 data 是 String ，time 是 Date，所以还是选择兼容。
     * 而且一开始前端要求 time 不是使用时间戳，所以就这样保留了，属于历史的遗留的一个缺陷。
     *
     * @param time
     * @param data
     * @param line
     */
    public LzNodeData(Instant time, Double data, int line) {
        this.time = time;
        this.data = String.valueOf(data);
        this.line = line;
    }
}
