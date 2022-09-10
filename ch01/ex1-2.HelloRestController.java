// 예제 1-2 JAX-RS 종단점

@ApplicationScoped
@Path("/hello")
public class HelloRestController {

    @Inject
    private HelloService helloService;

    @GET
    @Path("/{name}")
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name) {
	return helloService.sayHello(name);
    }
}
