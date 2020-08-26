package cn.litchi.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@TableName(value = "sys_user")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DBSysUser implements UserDetails, Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long orchardId;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String idcard;

    private Integer status;

    private Instant createTime;

    private Instant updateTime;

    @TableField(exist = false)
    private List<DBSysRole> roles;

    @TableField(exist = false)
    private Integer type;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(this.getRoles())) {
            return Collections.EMPTY_LIST;
        }
        return this.getRoles().stream()
                .map(it -> new SimpleGrantedAuthority(it.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}