// 예제 6-6 ClientJacksonProvider

public class ClientJacksonProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper = new ObjectMapper()
	.registerModule(new JavaTimeModule());

    @Override
    public ObjectMapper getContext(Class<?> type) {
	return mapper;
    }
}
