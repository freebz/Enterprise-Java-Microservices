// 예제 4-6 CategoryResource

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public Response create(Category category) throws Exception {
    if (category.getId() != null) {
	return Response
	    .status(Response.CONFLICT)
	    .entity("Unable to create Category, id was already set.")
	    .build();
    }

    Category parent;
    if ((parent = category.getParent()) != null && parent.getId() != null) {
	category.setParent(get(parent.getId()));
    }

    try {
	em.persist(category);
	em.flush();
    } catch (ConstraintViolationException cve) {
	return Response
	    .status(Response.Status.BAD_REQUEST)
	    .entity(cve.getMessage())
	    .build();
    } catch (Exception e) {
	return Response
	    .serverError()
	    .entity(e.getMessage())
	    .build();
    }
    return Response
	.created(new URI("category/" + category.getId().toString()))
	.build();
}
