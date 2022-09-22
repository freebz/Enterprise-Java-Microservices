// 예제 11-4 CategoryEventListener

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:#{KAFKA_SERVICE_PORT}")
public class CategoryEventListener {

    private static final String DATASOURCE =
	"java:/jboss/datasources/CayambeDS";

    @Consumer(topic = "category_topic", keyType = Integer.class,
        groupId = "cayambe-listener", offset = "earliest")
    public void handleEvent(Integer key, Category category) {
	if (null == category) {
	    // 카테고리 삭제
	    executeUpdateSQL("delete from category when category_id = " + key);
	    // 카테고리 계층구조로부터 카테고리 삭제
	    executeUpdateSQL("delete from category_category where category_id = " + key);
	    executeUpdateSQL("delete from category_category where parent_id = " + key);
	} else {
	    boolean update = rowExists("select * from category where category_id = " + key);
	    if (update) {
		// 카테고리 변경
		executeUpdateSQL("update category set name = '" + category.getName()
				 + "' header = '" + category.getHeader()
				 + "' image = '" + category.getImagePath()
				 + "' where category_id = " + key);
	    } else {
		// 카테고리 생성
		executeUpdateSQL("insert into category (id,name,header,visible,image) values("
				 + key + ",'" + category.getName() + "', '"
				 + category.getHeader() + "', " + (category.isVisible() ? 1 : 0)
				 + ", " + category.getImagePath() + "')");
		executeUpdateSQL("insert into category_category (category_id, parent_id)"
				 + "values (" + category.getId() + "," + category.getParent().getId() + ")");
	    }
	}
    }

    private void executeUpdateSQL(String sql) {
	Statement statement = null;
	Connection conn = null;

	try {
	    conn = getDatasource().getConnection();
	    statement = conn.createStatement();
	    statement.executeUpdate(sql);
	    statement.close();
	    conn.close();
	} catch (Exception e) {
	...
	}
    }

    private boolean rowExists(String sql) {
	Statement statement = null;
	Connection conn = null;
	ResultSet results = null;

	try {
	    conn = getDatasource().getConnection();
	    statement = conn.createStatement();
	    results = statement.executeQuery(sql);
	    return results.next();
	} catch (Exception e) {
	...
	}
	return dataSource;
    }

    private DataSource dataSource = null;
}
