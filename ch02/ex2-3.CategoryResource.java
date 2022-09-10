// 예제 2-3 CategoryResource의 @GET

@Path("/")
public class CategoryResource {

    @PersistenceContext(unitName = "AdminPU")
    private EntityManager em;

    @GET
    @Path("/categorytree")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryTree tree() throws Exception {
	return em.find(CategoryTree.class, 1);
    }
    ...
}
