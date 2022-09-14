// 예제 4-2 CategoryResource에서 모든 카테고리 읽어오기

@RunWith(Arquillian.class)
@DefaultDeployment
@RunAsClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryResourceTest {

    @Test
    public void aRetrieveAllCategories() throws Exception {
	Response response =
	    when()
	        .get("/admin/category")
	    .then()
	        .extract().response();

	String jsonAsString = response.asString();
	List<Map<String, ?>> jsonAsList = JsonPath.from(jsonAsString).getList("");

	assertThat(jsonAsList.size()).isEqualTo(21);

	Map<String, ?> record1 = jsonAsList.get(0);

	assertThat(record1.get("id")).isEqualTo(0);
	assertThat(record1.get("parent")).isNull();
	assertThat(record1.get("name")).isEqualTo("Top");
	assertThat(record1.get("visible")).isEqualTo(Boolean.TRUE);
    }
}
