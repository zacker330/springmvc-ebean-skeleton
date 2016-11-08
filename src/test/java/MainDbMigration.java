import com.avaje.ebean.config.dbplatform.DbPlatformName;
import com.avaje.ebean.dbmigration.DbMigration;
import hello.demo.component.DemoEbeanServerFactory;

/**
 * Created by jack on 1/27/16.
 */
public class MainDbMigration {
    public static void main(String[] args) throws Exception {


        DbMigration dbMigration = new DbMigration();

        dbMigration.setServer(new DemoEbeanServerFactory().getObject());
        // set path that the migration xml and ddl is located
        // this defaults to standard maven src/main/resources
        //dbMigration.setPathToResources("src/main/resources");

        // Postgres as the target DB
        dbMigration.setPlatform(DbPlatformName.MYSQL);

        // generate the migration xml and ddl
        dbMigration.generateMigration();
    }


}
