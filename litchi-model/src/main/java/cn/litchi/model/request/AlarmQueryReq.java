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
public class AlarmQueryReq {
    public static final int NODE_ID = 1;
    public static final int GROUP_ID = 2;

    private Integer queryType = -1;
    private String queryKey;
    private Instant beginDate = null;
    private Instant endDate = null;
}
