// 예제 6-3 java.net 비동기 기능을 활용한 DisplayResource

@GET
@Path("/async")
@Produces(MediaType.APPLICATION_JSON)
public void getCategoryTreeAsync(@Suspended final AsyncResponse asyncResponse) throws Exception {
    executorService().execute(() -> {
	    HttpURLConnection connection = null;

	    try {
		// 연결을 열고, 상태 코드를 검사하고, 응답을 처리하는 코드는
		// 동기적은 예제 코드와 같기 때문에 여기서는 생략했다.
		asyncResponse.resume(category);
	    } catch (IOException e) {
		asyncResponse.resume(e);
	    } finally {
		assert connection != null;
		connection.disconnect();
	    }
	});
}
