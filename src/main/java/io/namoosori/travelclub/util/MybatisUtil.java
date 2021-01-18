package io.namoosori.travelclub.util;

import io.namoosori.travelclub.step4.mapper.BoardMapper;
import io.namoosori.travelclub.step4.mapper.ClubMapper;
import io.namoosori.travelclub.step4.mapper.MemberMapper;
import io.namoosori.travelclub.step4.mapper.PostingMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    private static void init(){
        if (sqlSessionFactory == null){
            DataSource dataSource = ConnectionUtil.getDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);

            //add Mappers...
            configuration.addMapper(BoardMapper.class);
            configuration.addMapper(ClubMapper.class);
            //configuration.addMapper(MemberMapper.class);
            //configuration.addMapper(PostingMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
    }

    public static SqlSession createSession(){
        init();
        return sqlSessionFactory.openSession(true);
    }
}