package cn.litchi.model.mybatis;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class ListTypeHandler implements TypeHandler<List<Long>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        String hobbys = StringUtils.join(parameter, ",");
        try {
            ps.setString(i, hobbys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String hobbys = cs.getString(columnIndex);
        return Arrays.asList(hobbys.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public List<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Arrays.asList(rs.getString(columnIndex).split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public List<Long> getResult(ResultSet rs, String columnName) throws SQLException {
        return Arrays.asList(rs.getString(columnName).split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
    }
}