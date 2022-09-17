// 예제 6-7 JAX-RS 클라이언트를 사용하는 DisplayResource

@GET
@Path("/sync")
@Produces(MediaType.APPLICATION_JSON)
public Category getCategoryTreeSync() {
    Client client = ClientBuilder.newClient();

    return client
	.register(ClientJacksonProvider.class)
	.target(this.categoryUrl)
	.request(MediaType.APPLICATION_JSON)
	.get(Category.class);
}
