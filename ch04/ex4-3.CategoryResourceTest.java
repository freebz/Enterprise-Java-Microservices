// 예제 4-3 CategoryResource에서 모든 카테고리 읽어오기

@Test
public void bRetrieveCategory() throws Exception {
    Response response =
	given()
	.pathParam("categoryId", 1024)
	.when()
	.get("/admin/category/{categoryId}")
	.then()
	.extract().response();

    String jsonAsString = response.asString();

    Category category = JsonPath.from(jsonAsString).getObject("", Category.class);

    assertThat(category.getId()).isEqualTo(1014);
    assertThat(category.getParent().getId()).isEqualTo(1011);
    assertThat(category.getName()).isEqualTo("Ford SUVs");
    assertThat(category.isVisible()).isEqualTo(Boolean.TRUE);
}
