// 예제 6-9 JAX-RS 클라이언트와 InvocationCallback를 활용하는 DisplayResource

@GET
@Path("/asyncAlt")
@Produces(MediaType.APPLICATION_JSON)
public void getCategoryTreeAsyncAlt(@Suspended final AsyncResponse asyncResponse) {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(this.categoryUrl)
	.register(ClientJacksonProvider.class);
    target.request(MediaType.APPLICATION_JSON)
	.async()
	.get(new InvocationCallback<Category>() {
		@Override
		public void completed(Category result) {
		    asyncResponse.resume(result);
		}
		@Override
		public void failed(Throwable throwable) {
		    throwable.printStackTrace();
		    asyncResponse.resume(Response
					 .serverError()
					 .entity(throwable.getMessage())
					 .build());
		}
	    });
}
