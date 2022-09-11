package ejm.shopping;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class JaxrsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
	    Set<Class<?>> resources = new HashSet<>();
	    resources.add(CartController.class);
	    return resources;
    }
}
