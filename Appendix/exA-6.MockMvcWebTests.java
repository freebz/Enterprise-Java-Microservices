// 예제 A-6 MockMvcWebTests

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplication(
    classes = ReadingListApplicationTests.class)
@WebAppConfiguration
public class MockMvcWebTests {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
	mockMvc = MockMvcBuilders
	    .webAppContextSetup(webContext)
	    .build();
    }
}
