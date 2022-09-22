// 예제 11-2 CategoryResource

@Path("/")
@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:${KAFKA_SERVICE_PORT}")
public class CategoryResource {
...
    @Producer
    private SimpleKafkaProducer<Integer, Category> producer;
...
    public Resource create(Category category) throws Exception {
...
	producer.send("category_topic", category.getId(), category);
...
    public Response remove(@PathParam("categoryId") Integer categoryId,
        @Context SecurityContext context) throws Exception {
...
	producer.send("category_topic", categoryId, null);
...
}
