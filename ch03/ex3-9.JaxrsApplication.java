// 예제 3-9 파야라 JaxrsApplication

@ApplicationPath("/")
public class JaxrsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
	Set<Class<?>> resources = new HashSet<>();
	resources.add(CartController.class);
	return resources;
    }
}
