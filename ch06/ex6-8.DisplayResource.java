// 예제 6-8 JAX-RS 클라이언트와 @Suspended를 사용하는 DisplayResource

@GET
@Path("/async")
@Produces(MediaType.APPLICATION_JSON)
public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
    executorService().execute(() -> {
	    Client client = ClientBuilder.newClient();

	    try {
		Category category = client.target(this.categoryUrl)
		    .register(ClientJacksonProvider.class)
		    .request(MediaType.APPLICATION_JSON)
		    .get(Category.class);

		asyncResponse.resume(category);
	    } catch (Exception e) {
		asyncResponse.resume(Response
				     .serverError()
				     .entity(e.getMessage())
				     .build());
	    }
	});
}
