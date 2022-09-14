// 에제 5-4 CategoryResourceIT

@RunWith(Arquillian.class)
public class CategoryResourceIT {

    @RouteURL("chapter5-admin")
    private URL url;

    @Before
    public void verifyRunning() {
	await()
	    .atMost(2, TimeUnit.MINUTES)
	    .until(() -> {
		    try {
			return get(url + "admin/category").statusCode() == 200;
		    } catch (Exception e) {
			return false;
		    }
		});

	RestAssured.baseURI = url + "admin/category";
    }

    @Test
    public void testGetCategory() throws Exception {
	Response response =
	    given()
	        .pathParam("categoryId", 1014)
	    .when()
	        .get("/{categoryId}")
	    .then()
	        .statusCode(200)
	        .extract().response();

	String jsonAsString = response.asString();

	Category category = JsonPath.from(jsonAsString).getObject("", Category.class);

	assertThat(category.getId()).isEqualTo(1014);
	assertThat(category.getParent().getId()).isEqualTo(1011);
	assertThat(category.getName()).isEqualTo("Ford SUVs");
	assertThat(category.isVisible()).isEqualTo(Boolean.TRUE);
    }
}
