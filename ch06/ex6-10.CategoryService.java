// 예제 6-10 CategoryService

@Path("/admin/categorytree")
public interface CategoryService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Category getCategoryTree();
}
