package com.hupa.exp.servermng.util;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public class SqlSessionFactoryUtil {
    public static SqlSessionFactory createSqlSessionFactory(DataSource dataSource, final String typeAliasesPackage,
                                                            final String typeHandlersPackage, final String mapperLocations,
                                                            final Configuration mybatisConfig, Interceptor[] plugins){
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            sqlSessionFactoryBean.setTypeHandlersPackage(typeHandlersPackage);
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
            sqlSessionFactoryBean.setConfiguration(mybatisConfig);
            sqlSessionFactoryBean.setPlugins(plugins);
            return sqlSessionFactoryBean.getObject();
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
