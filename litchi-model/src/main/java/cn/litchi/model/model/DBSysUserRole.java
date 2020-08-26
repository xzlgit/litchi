package cn.litchi.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@TableName(value = "sys_user_role")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DBSysUserRole {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long sysUserId;

    private Long sysRoleId;
}
