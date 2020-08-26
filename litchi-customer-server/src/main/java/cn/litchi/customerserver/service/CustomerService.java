package cn.litchi.customerserver.service;

import cn.litchi.model.mapper.SysRoleDao;
import cn.litchi.model.mapper.SysUserDao;
import cn.litchi.model.mapper.SysUserRoleDao;
import cn.litchi.model.model.DBLzNode;
import cn.litchi.model.model.DBSysRole;
import cn.litchi.model.model.DBSysUser;
import cn.litchi.model.model.DBSysUserRole;
import cn.litchi.model.request.NodeQueryReq;
import cn.litchi.model.request.UserQueryReq;
import cn.litchi.rpc.UserServiceRpc;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class CustomerService implements UserServiceRpc {

    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysUserRoleDao userRoleDao;

    @Override
//    @Cacheable(value = "litchi:data:user-get-user-by-name",
//            key = "':' + #name",
//            unless = "#result==null")
    public DBSysUser getUserByName(@RequestParam("name") String name) {
        return userDao.findByUserName(name);
    }

    @Override
    public DBSysUser addUser(@RequestBody DBSysUser user, @RequestParam("role") String role) {
        DBSysUser exitUser = userDao.findByUserName(user.getUsername());
        if (exitUser != null) {
            return null;
        }
        userDao.insert(user);
        QueryWrapper<DBSysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DBSysRole::getName, role);
        DBSysRole sysRole = roleDao.selectOne(queryWrapper);
        // 设置新用户对应的角色
        DBSysUserRole userRole = DBSysUserRole.builder()
                .sysRoleId(sysRole.getId())
                .sysUserId(user.getId())
                .build();
        userRoleDao.insert(userRole);
        return user;
    }

    @Override
    public List<DBSysUser> getUserAndAdminList(@RequestParam(value = "offset", required = false) int offset,
                                               @RequestParam(value = "limit", required = false) int limit) {
        return userDao.selectList(null);
    }

    @Override
    public List<DBSysUser> queryUser(@RequestBody UserQueryReq req) {
        int type = req.getQueryType().intValue();
        String queryKey = req.getQueryKey();
        QueryWrapper<DBSysUser> queryWrapper = new QueryWrapper<>();
        if (type == UserQueryReq.USER_ID) {
            queryWrapper.lambda().eq(DBSysUser::getId, Long.valueOf(queryKey));
        } else if (type == UserQueryReq.USER_NAME) {
            queryWrapper.lambda().like(DBSysUser::getUsername, queryKey);
        } else if (type == UserQueryReq.USER_EMAIL) {
            queryWrapper.lambda().eq(DBSysUser::getEmail, queryKey);
        } else if (type == UserQueryReq.USER_ID_CARD) {
            queryWrapper.lambda().eq(DBSysUser::getIdcard, queryKey);
        } else if (type == UserQueryReq.USER_PHONE) {
            queryWrapper.lambda().eq(DBSysUser::getPhone, queryKey);
        }
        List<DBSysUser> users = userDao.selectList(queryWrapper);
        users.stream().forEach(it -> it.setPassword(null));
        return users;
    }

    @Override
    public DBSysUser updateUser(@RequestBody DBSysUser user) {
        user.setUpdateTime(Instant.now());
        userDao.updateById(user);
        return user;
    }


}
