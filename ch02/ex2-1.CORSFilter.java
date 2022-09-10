// 예제 2-1 CORSFilter

public class CORSFilter imploements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext,
		       ContainerResponseContext responseContext) throws IOException {
	responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
	responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept authorization");
	responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
	responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
