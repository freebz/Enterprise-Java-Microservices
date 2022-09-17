// 예제 6-1 Category 모델 클래스

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {
    protected Integer id;
    protected String name;
    protected String header;
    protected Boolean visible;
    protected String imagePath;
    protected Category parent;
    private Collection<Category> children = new HashSet<>();
    protected LocalDateTime created = LocalDateTime.now();
    protected Integer version;
    ...
}
