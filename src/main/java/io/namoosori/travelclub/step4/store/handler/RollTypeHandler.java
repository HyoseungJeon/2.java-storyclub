package io.namoosori.travelclub.step4.store.handler;

import io.namoosori.travelclub.step1.entity.club.RoleInClub;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RollTypeHandler extends BaseTypeHandler<RoleInClub> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RoleInClub roleInClub, JdbcType jdbcType) throws SQLException {
        ps.setString(i, roleInClub.name());
    }

    @Override
    public RoleInClub getNullableResult(ResultSet rs, String s) throws SQLException {
        return RoleInClub.valueOf(rs.getString(s));
    }

    @Override
    public RoleInClub getNullableResult(ResultSet rs, int i) throws SQLException {
        return RoleInClub.valueOf(rs.getString(i));
    }

    @Override
    public RoleInClub getNullableResult(CallableStatement cs, int i) throws SQLException {
        return RoleInClub.valueOf(cs.getString(i));
    }
}
