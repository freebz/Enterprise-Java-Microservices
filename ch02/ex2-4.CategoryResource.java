// 예제 2-4 CategoryResource의 @DELETE

@Path("/")
public class CategoryResource {
    ...

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{categoryId}")
    @Transactional
    public Response remove(@PathParam("categoryId") Integer categoryId)
	      throws Exception {
	try {
	    Category entity = em.find(Category.class, categoryId);
	    em.remove(entity);
	} catch (Exception e) {

	    return Response
		.serverError()
		.entity(e.getMessage())
		.build();
	}

	return Response
	    .noContent()
	    .build();
    }
    ...
}
