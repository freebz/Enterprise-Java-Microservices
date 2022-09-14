// 예제 4-7 CategoryResourceTest에서 카테고리 생성에 실패

@Test
public void dFailToCreateCategoryFromNullName() throws Exception {
    Category badCategory = new Category();
    badCategory.setVisible(Boolean.TRUE);
    badCategory.setHeader("header");
    badCategory.setImagePath("n/a");
    badCategory.setParent(new TestCategoryObject(1009));

    Response response =
	given()
	    .contentType(ContentType.JSON)
	    .body(badCategory)
	.when()
	    .post("/admin/category");

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(400);

    ...

    response =
	when()
	    .get("/admin/category")
	.then()
	    .extract().response();

    String jsonAsString = response.asString();
    List<Map<Stirng, ?>> jsonAsList =
	JsonPath.from(jsonAsString).getList("");

    assertThat(jsonAsList.size()).isEqualTo(22);
}
