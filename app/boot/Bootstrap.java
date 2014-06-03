package boot;

import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
import models.User;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
        if(Linker.count() == 0) {
           Fixtures.loadModels("initData.yml");
       }
    }
 
}