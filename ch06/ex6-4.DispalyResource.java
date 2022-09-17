// 예제 6-4 HttpClient를 사용해 만든 DisplayResource

@GET
@Path("/sync")
@Produces(MediaType.APPLICATION_JSON)
public Category getCategoryTreeSync() throws Exception {
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	HttpGet get = new HttpGet(this.categoryUrl);
	get.addHeader("Accept", MediaType.APPLICATION_JSON);

	return httpClient.execute(get, response -> {
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
		    return new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.reeadValue(response.getEntity().getContent(), Category.class);
		} else {
		    throw new ClientProtocolException("Unexpected response status: " + status);
		}
	    });
    }
}
