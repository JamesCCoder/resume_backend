package backend.backend.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = {"backend.backend.students", "backend.backend.professors"},
        entityManagerFactoryRef = "studentsEntityManagerFactory",
        transactionManagerRef = "studentsTransactionManager"
)
public class StudentsDataSourceConfig {

    @Primary
    @Bean(name = "studentsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.students")
    public DataSource studentsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "studentsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentsEntityManagerFactory(
            @Qualifier("studentsDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("backend.backend.students", "backend.backend.professors");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean(name = "studentsTransactionManager")
    public PlatformTransactionManager studentsTransactionManager(
            @Qualifier("studentsEntityManagerFactory") LocalContainerEntityManagerFactoryBean studentsEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(studentsEntityManagerFactory.getObject());
        return transactionManager;
    }
}
