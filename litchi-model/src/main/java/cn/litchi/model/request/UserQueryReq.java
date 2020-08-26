package cn.litchi.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryReq {
    public static final int USER_ID = 1;
    public static final int USER_NAME = 2;
    public static final int USER_EMAIL = 3;
    public static final int USER_ID_CARD = 4;
    public static final int USER_PHONE = 5;

    private Integer queryType = -1;
    private String queryKey;
}
