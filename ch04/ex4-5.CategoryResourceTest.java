// 예제 4-5 CategoryResourceTest 안에서 카테고리 만들기

@Test
public void cCreateCategory() throws Exception {
    Category bmwCategory = new Category();
    bmwCategory.setName("BMW");
    bmwCategory.setVisible(Boolean.TRUE);
    bmwCategory.setHeader("header");
    bmwCategory.setImagePath("n/a");
    bmwCategory.setParent(new TestCategoryObject(1009));

    Response response =
	given()
	    .contentType(ContentType.JSON)
	    .body(bmwCategory)
	.when()
	    .post("/admin/category");

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(201);
    String locationUrl = response.getHeader("Location");
    Integer categoryId = Integer.valueOf(locationUrl.substring(locationUrl.lastIndexOf('/') + 1));

    response =
	when()
	    .get("/admin/category")
	.then()
	    .extract().response();

    String jsonAsString = response.asString();
    List<Map<String, ?>> jsonAsList = JsonPath.from(jsonAsString).getList("");

    assertThat(jsonAsList.size()).isEqualTo(22);

    response =
	given()
	    .pathParam("categoryId", categoryId)
	.when()
	    .get("/admin/category/{categoryId}")
	.then()
	    .extract().response();

    jsonAsString = response.asString();
    Category category = JsonPath.from(jsonAsString).getObject("", Category.class);

    assertThat(category.getId()).isEqualTo(categoryId);
    assertThat(category.getParent().getId()).isEqualTo(1009);
    assertThat(category.getName()).isEqualTo("BMW");
    assertThat(category.isVisible()).isEqualTo(Boolean.TRUE);
}
