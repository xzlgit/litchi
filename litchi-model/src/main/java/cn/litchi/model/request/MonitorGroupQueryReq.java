package cn.litchi.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorGroupQueryReq {
    public static final int GROUP_ID = 1;
    public static final int NODE_IDS = 2;
    public static final int GROUP_NAME = 3;

    private Integer queryType = -1;
    private String queryKey;
    private Instant beginDate = null;
    private Instant endDate = null;
}
