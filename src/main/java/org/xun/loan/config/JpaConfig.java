package org.xun.loan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.SpringSessionContext;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

//@Configuration
@PropertySource(value = { "classpath:application.yml" })

public class JpaConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUsername(environment.getRequiredProperty("rong.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("rong.datasource.password"));
        dataSource.setUrl(environment.getRequiredProperty("rong.datasource.url"));
//        dataSource.setInitialSize(lionUtil.getIntValue(LionKeys.MYSQL_INITIALSIZE));
//        dataSource.setMaxWait(lionUtil.getIntValue(LionKeys.MYSQL_MAXWAIT));
//        dataSource.setMaxActive(lionUtil.getIntValue(LionKeys.MYSQL_MAXACTIVE));
        dataSource.setFilters("stat");
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(environment.getRequiredProperty("spring.jpa.show-sql", boolean.class));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.xun.entity");
        Properties props = new Properties();
        props.put("hibernate.format_sql", true);
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        props.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        props.put("hibernate.current_session_context_class", SpringSessionContext.class.getName());
        factory.setJpaProperties(props);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(AbstractEntityManagerFactoryBean factoryBean){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factoryBean.getObject());
        return transactionManager;
    }

}

