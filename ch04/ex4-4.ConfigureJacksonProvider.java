// 예제 4-4 ConfigureJacksonProvider

@Provider
public class ConfigureJacksonProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper = new ObjectMapper()
	.registerModule(new JavaTimeModule());

    @Override
    public ObjectMapper getContext(Class<?> type) {
	return mapper;
    }
}
