package cn.litchi.model.mapper;

import cn.litchi.model.model.DBSysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserDao extends BaseMapper<DBSysUser> {

    DBSysUser findByUserName(@Param("name") String name);
}
