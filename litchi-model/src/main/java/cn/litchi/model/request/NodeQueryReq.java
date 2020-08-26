package cn.litchi.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeQueryReq {
    public static final int NODE_ID = 1;
    public static final int NODE_NAME = 2;
    public static final int NODE_TYPE = 3;
    public static final int NODE_TOKEN = 4;

    private Integer queryType = -1;
    private String queryKey;
}
