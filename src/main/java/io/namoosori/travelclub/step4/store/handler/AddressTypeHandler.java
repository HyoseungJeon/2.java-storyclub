package io.namoosori.travelclub.step4.store.handler;

import io.namoosori.travelclub.step1.entity.club.Address;
import io.namoosori.travelclub.util.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressTypeHandler extends BaseTypeHandler<List<Address>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Address> addresses, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtil.toJson(addresses));
    }

    @Override
    public List<Address> getNullableResult(ResultSet rs, String s) throws SQLException {
        String jsonString = rs.getString(s);
        return JsonUtil.fromJsonList(jsonString,Address.class);
    }

    @Override
    public List<Address> getNullableResult(ResultSet rs, int i) throws SQLException {
        String jsonString = rs.getString(i);
        return JsonUtil.fromJsonList(jsonString,Address.class);
    }

    @Override
    public List<Address> getNullableResult(CallableStatement cs, int i) throws SQLException {
        String jsonString = cs.getString(i);
        return JsonUtil.fromJsonList(jsonString,Address.class);
    }
}
