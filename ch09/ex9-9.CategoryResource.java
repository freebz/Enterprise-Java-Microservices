// 예제 9-9 CategoryResource

@DELETE
@Produces(MediaType.APPLICATION_JSON)
@Path("/category/{categoryId}")
@Transactional
public Response remove(
        @PathParam("categoryId") Integer categoryId,
	@Context SecurityContext context) throws Exception {
    String username = "";

    if (context.getUserPrincipal() instanceof KeycloakPrincipal) {
	KeycloakPrincipal<KeycloakSecurityContext> kp =
	    (KeycloakPrincipal<KeycloakSecurityContext>)
	    context.getUserPrincipal();

	username = kp.getKeycloakSecurityContext().getToken().getName();
    }

    try {
	Category entity = em.find(Category.class, categoryId);
	em.remove(entity);
	System.out.println(username + " is deleting category with id: " + categoryId);
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
