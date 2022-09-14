// 예제 4-10 ConsumerPactTest

public class ConsumerPactTest extends ConsumerPactTestMk2 {
    private Category createCategory(Integer id, String name) {
	Category cat = new TestCategoryObject(id, LocalDateTime.parse("2002-01-01T00:00:00"), 1);
	cat.setName(name);
	cat.setVisible(Boolean.TRUE);
	cat.setHeader("header");
	cat.setImagePath("n/a");

	return cat;
    }

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
	Category top = createCategory(0, "Top");

	Category transport = createCategory(1000, "Transportation");
	transport.setParent(top);

	Category autos = createCategory(1002, "Autoboiles");
	autos.setParent(transport);

	Category cars = createCategory(1009, "Cars");
	cars.setParent(autos);

	Category toyotas = createCategory(1015, "Toyota Cars");
	toyotas.setParent(cars);

	ObjectMapper mapper = new ObjectMapper()
	    .registerModule(new JavaTimeModule());

	try {
	    return builder
		.uponReceiving("Retrieve a category")
		    .path("/admin/category/1015")
		    .method("GET")
		.willRespondWith()
		    .status(200)
		    .body(mapper.writeValueAsString(toyotas))
		.toPact();
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}

	return null;
    }

    @Override
    protected String providerName() {
	return "admin_service_provider";
    }

    @Override
    protected String consumerName() {
	return "admin_client_consumer";
    }

    @Override
    protected PactSpecVersion getSpecificationVersion() {
	return PactSpecVersion.V3;
    }

    @Override
    protected void runTest(MockServer mockServer) throws IOException {
	Category cat = new AdminClient(mockServer.getUrl()).getCategory(1015);

	Assertions.assertThat(cat).isNotNull();
	assertThat(cat.getId()).isEqualTo(1015);
	assertThat(cat.getName()).isEqualTo("Toyota Cars");
	assertThat(cat.getHeader()).isEqualTo("header");
	assertThat(cat.getImagePath()).isEqualTo("n/a");
	assertThat(cat.isVisible()).isTrue();
	assertThat(cat.getParent()).isNotNull();
	assertThat(cat.getParent().getId()).isEqualTo(1009);
    }
}
