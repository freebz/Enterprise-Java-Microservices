// 예제 6-12 @Suspended와 레스트이지를 사용하는 DisplayResource

@GET
@Path("/async")
@Produces(MediaType.APPLICATION_JSON)
public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
    executorService().execute(() -> {
	    ResteasyClient client = new ResteasyClientBuilder().build();

	    try {
		ResteasyWebTarget target = client.target(this.categoryUrl)
		    .register(ClientJacksonProvider.class);
		CategoryService categoryService =
		    target.proxy(CategoryService.class);
		Category category = categoryService.getCategoryTree();
		asyncResponse.resume(category);
	    } catch (Exception e) {
		asyncResponse.resume(Response
				     .serverError()
				     .entity(e.getMessage())
				     .build());
	    }
	});
}
