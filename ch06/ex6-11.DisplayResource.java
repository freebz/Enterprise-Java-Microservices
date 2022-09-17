// 예제 6-11 레스트이지를 사용하는 DisplayResource

@GET
@Path("/sync")
@Produces(MediaType.APPLICATION_JSON)
public Category getCategoryTreeSync() {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(this.categoryUrl)
	.register(ClientJacksonProvider.class);
    CategoryService categoryService = target.proxy(CategoryService.class);
    return categoryService.getCategoryTree();
}
