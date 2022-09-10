// 예제 2-5 CategoryResource의 @POST

@Path("/")
public class CategoryResource {
    ...

    @POST
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GTransactional
    public Response create(Category category) throws Exception {
	if (category.getId() != null) {
	    return Response
		.status(Response.Status.CONFLICT)
		.entity("Unable to create Category. id was already set.")
		.build();
	}

	try {
	    em.persist(category);
	} catch (Exception e) {
	    return Response
		.serverError()
		.entity(e.getMessage())
		.build();
	}

	return Response
	    .created(new URI(category.getId().toString()))
	    .build();
    }
    ...
}
