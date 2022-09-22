// 예제 A-7 MockMvcWebTests

@Test
public void postBook() throws Exception {
    mockMvc.perform(post("/readingList")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	.param("title", "BOOK TITLE")
	.param("author", "BOOK AUTHOR")
	.param("isbn", "1234567890")
        .param("description", "DESCRIPTION"))
	.andExpect(status().is3xxRedirection())
	.andExpect(header().string("Location", "/readingList"));

    Book expectedBook = new Book();
    expectedBook.setId(1L);
    expectedBook.setReader("craig");
    expectedBook.setTitle("BOOK TITLE");
    expectedBook.setAuthor("BOOK AUTHOR");
    expectedBook.setIsbn("1234567890");
    expectedBook.setDescription("DESCRIPTION");

    mockMvc.perform(get("/readingList"))
	.andExpect(status().isOk())
	.andExpect(view().name("readingList"))
	.andExpect(model().attributeExists("books"))
	.andExpect(model().attribute("books", hasSize(1)))
	.andExpect(model().attribute("books",
	         contains(samePropertyValuesAs(expectedBook))));
