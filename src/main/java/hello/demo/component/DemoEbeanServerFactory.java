package hello.demo.component;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by jack on 1/27/16.
 */
@Component
public class DemoEbeanServerFactory implements FactoryBean<EbeanServer> {

    @Override
    public EbeanServer getObject() throws Exception {
        ServerConfig config = new ServerConfig();
        config.setName("mysql");
        config.setCurrentUserProvider(() -> "mysql");
        config.setDatabasePlatformName("mysql");
        config.loadFromProperties();


        config.setDefaultServer(true);
        config.setRegister(true);

        return EbeanServerFactory.create(config);
    }

    @Override
    public Class<?> getObjectType() {
        return EbeanServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
