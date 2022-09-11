// 예제 3-4 애노테이션을 추가한 CartController 메서드

@GET
@Produces(MediaType.APPLICATION_JSON)
public List<CartItem> all() throws Exception {}

@GET
@Path("/add")
public String addOrUpdateItem(
    @QueryParam("item") String itemName,
    @QueryParam("qty") Integer qty) throws Exception {
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/get/{itemName}")
public CartItem getItem(
    @PathParam("itemName") String itemName) throws Exception {
}
