// 예제 6-2 java.net을 활용한 DisplayResource

@GET
@Path("/sync")
@Produces(MediaType.APPLICATION_JSON)
public Category getCategoryTreeSync() throws Exception {
    HttpURLConnection connection = null;

    try {
	URL url = new URL(this.categoryUrl);
	connection = (HttpURLConnection) url.openConnection();

	connection.setRequestMethod("GET");
	connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

	if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
	    throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
	}

	return new ObjectMapper()
	    .registerModule(new JavaTimeModule())
	    .readValue(connection.getInputStream(), Category.class);

    } finally {
	assert connection != null;
	connection.disconnect();
    }
}
