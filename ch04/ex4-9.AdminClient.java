// 예제 4-9 AdminClient

public class AdminClient {
    private String url;

    public AdminClient(String url) {
	this.url = url;
    }

    public Category getCategory(final Integer categoryId) throws IOException {
	URIBuilder uriBuilder;
	try {
	    uriBuilder = new URIBuilder(url).setPath("/admin/category/" + categoryId);
	} catch (URISyntaxException e) {
	    throw new RuntimeException(e);
	}

	String jsonResponse =
	    Request
	        .Get(uriBuilder.toString())
	        .execute()
	        .returnContent().asString();

	if (jsonResponse.isEmpty()) {
	    return null;
	}

	return new ObjectMapper()
	    .registerModule(new JavaTimeModule())
	    .readValue(jsonResponse, Category.class);
    }
}
