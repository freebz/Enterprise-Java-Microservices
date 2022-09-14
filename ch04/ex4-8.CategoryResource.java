// 예제 4-8 CategoryResource.create()

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
