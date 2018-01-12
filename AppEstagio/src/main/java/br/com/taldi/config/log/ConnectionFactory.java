package br.com.taldi.config.log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConnectionFactory {
	
	private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    
	
    private final DataSource dataSource;
    
    private static interface Singleton {
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }
	
    private ConnectionFactory() {
    	Properties applicationProperties = new Properties();
    	try {
    		logger.info("Configuração do log no banco de dados.");
    		Resource resource = new ClassPathResource("/application.properties");
    		applicationProperties = PropertiesLoaderUtils.loadProperties(resource);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
    	
    	Properties logProperties = new Properties();
    	logProperties.setProperty("user", applicationProperties.getProperty("spring.datasource.username"));
    	logProperties.setProperty("password", applicationProperties.getProperty("spring.datasource.password")); 
        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
        		applicationProperties.getProperty("spring.datasource.url"), logProperties
        );
        new PoolableConnectionFactory(
                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED
        );
 
        this.dataSource = new PoolingDataSource(pool);
    }
    
    public static Connection getDatabaseConnection() throws SQLException {
    	return Singleton.INSTANCE.dataSource.getConnection();
    }

}
