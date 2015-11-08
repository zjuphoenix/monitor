package com.zju.als.monitor;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2015/11/3.
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.zju.als.monitor.guardian.dao")
public class DBConfig {
    /**
     * SpringApplication will load properties from application.properties files in the following locations and add them to the Spring Environment:
     * 1.A /config subdir of the current directory.
     * 2.The current directory
     * 3.A classpath /config package
     * 4.The classpath root
     */
    @Autowired
    private Environment env;

    /*@Autowired
    private DataSource dataSource;*/

    @Bean(destroyMethod="close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(env.getProperty("bonecp.jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("bonecp.jdbc.url"));
        dataSource.setUsername(env.getProperty("bonecp.jdbc.username"));
        dataSource.setPassword(env.getProperty("bonecp.jdbc.password"));
        dataSource.setIdleConnectionTestPeriodInMinutes(Long.parseLong(env.getProperty("bonecp.jdbc.idleConnectionTestPeriodInMinutes")));
        dataSource.setIdleMaxAgeInMinutes(Long.parseLong(env.getProperty("bonecp.jdbc.idleMaxAgeInMinutes")));
        dataSource.setMaxConnectionsPerPartition(Integer.parseInt(env.getProperty("bonecp.jdbc.maxConnectionsPerPartition")));
        dataSource.setMinConnectionsPerPartition(Integer.parseInt(env.getProperty("bonecp.jdbc.minConnectionsPerPartition")));
        dataSource.setPartitionCount(Integer.parseInt(env.getProperty("bonecp.jdbc.partitionCount")));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("bonecp.jdbc.acquireIncrement")));
        dataSource.setStatementsCacheSize(Integer.parseInt(env.getProperty("bonecp.jdbc.statementsCacheSize")));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        /*PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/orm*//*.xml"));*/
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "txManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
