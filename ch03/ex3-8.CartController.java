// 예제 3-8 CartController

@Path("/")
public class CartController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> all() throws Exception {}

    @GET
    @Path("/add")
    public String addOrUpdateItem(
        @QueryParam("Item") String itemName,
	@QueryParam("qty") Integer qty) throws Exception {
    }
}
