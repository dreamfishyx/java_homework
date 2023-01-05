package com.dreamfish.admintest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringJUnitConfig(locations = {"classpath:spring.xml"})
public class ConTest {
    @Autowired
    private DataSource source;

    @Test
    public void test1() throws SQLException {
        Connection connection = source.getConnection();
        System.out.println(connection);
    }

}
