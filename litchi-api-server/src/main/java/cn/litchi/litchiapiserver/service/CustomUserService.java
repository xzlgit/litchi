package cn.litchi.litchiapiserver.service;

import cn.litchi.model.mapper.SysUserDao;
import cn.litchi.model.model.DBSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserService implements UserDetailsService {

    @Autowired
    private SysUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DBSysUser user = userDao.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
