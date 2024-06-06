//package backend.backend.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "backend.backend.administer",
//        entityManagerFactoryRef = "administerEntityManagerFactory",
//        transactionManagerRef = "administerTransactionManager"
//)
//public class AdministerDataSourceConfig {
//
//    @Bean(name = "administerDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.administer")
//    public DataSource administerDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "administerEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean administerEntityManagerFactory(
//            @Qualifier("administerDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan(new String[] { "backend.backend.administer" });
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean(name = "administerTransactionManager")
//    public PlatformTransactionManager administerTransactionManager(
//            @Qualifier("administerEntityManagerFactory") LocalContainerEntityManagerFactoryBean administerEntityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(administerEntityManagerFactory.getObject());
//        return transactionManager;
//    }
//}
