// 예제 1-1 CDI 서비스

@RequestScoped
public class HelloService {

    public String sayHello(String name) {
	return "Hello " + name;
    }
}
