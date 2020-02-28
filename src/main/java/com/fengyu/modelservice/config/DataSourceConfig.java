package com.fengyu.modelservice.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "profileSqlSessionFactory")
public class DataSourceConfig {
    // 数据源目录
    static final String PACKAGE = "com.fengyu.modelservce.dao";
    static final String MAPPER_LOCATION = "classpath:mappers/*.xml";
    /**
     * 数据库连接.
     */
    @Value("${spring.datasource.url}")
    private String url;
    /**
     * 用户.
     */
    @Value("${spring.datasource.username}")
    private String username;
    /**
     * 密码.
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * DataSource.
     * @return
     */
    @Bean(name= "profileWarDataSource")
    @Primary
    public DataSource profileDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(30);
        dataSource.setMinIdle(30);
        dataSource.setMaxActive(70);
        dataSource.setTimeBetweenConnectErrorMillis(6000000);
        dataSource.setMaxWait(90000000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }

    /**
     * 事务管理.
     * @return profileTranscatonManager
     */
    @Bean(name = "profileTranscatonManager")
    @Primary
    public DataSourceTransactionManager profileTranscatonManager() {
        return new DataSourceTransactionManager(profileDataSource());
    }

    /**
     * 会话工厂
     * @param profileDataSource 数据源
     * @return SqlSessionFactory
     * @throws Exception 运行时异常
     */
    @Bean(name = "profileSqlSessionFactory")
    @Primary
    public SqlSessionFactory profileSqlSessionFactory(@Qualifier("profileWarDataSource") DataSource profileDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(profileDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION)
        );
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }
}
