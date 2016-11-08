package hello.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration()
@Import(
        WebMvcConfig.class
)
public class MainConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MainConfig.class);

    @Autowired
    private Environment env;

    @PostConstruct
    public void initApp() {
        LOG.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            LOG.info("No Spring profile configured, running with default configuration.");
        } else {
            for (String profile : env.getActiveProfiles()) {
                LOG.info("Detected Spring profile: {}", profile);
            }
        }


        /**
         * enhance ebean model
         */
//        if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent",
//                "debug=1;packages=net.oschina.ml.domain.**")) {
//            LOG.info("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
//        }
    }

}
