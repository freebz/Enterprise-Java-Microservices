// 예제 4-1 CategoryTest

public class CategoryTest {

    @Test
    public void categoriesAreEqual() throws Exception {
	LocalDateTime now = LocalDateTime.now();
	Category cat1 = createCategory(1, "Top", Boolean.TRUE, now);
	Category cat2 = createCategory(1, "Top", Boolean.TRUE, now);

	Assertions.assertThat(cat1).isEqualTo(cat2);
	assertThat(cat1.equals(cat2)).isTrue();
	assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
    }

    @Test
    public void categoryModification() throws Exception {
	LocalDateTime now = LocalDateTime.now();
	Category cat1 = createCategory(1, "Top", Boolean.TRUE, now);
	Category cat2 = createCategory(1, "Top", Boolean.TRUE, now);

	Assertions.assertThat(cat1).isEqualTo(cat2);
	assertThat(cat1.equals(cat2)).isTrue();
	assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
	cat1.setVisible(Boolean.FALSE);

	Assertions.assertThat(cat1).isNotEqualTo(cat2);
	assertThat(cat1.equals(cat2)).isFalse();
	assertThat(cat1.hashCode()).isNotEqualTo(cat2.hashCode());
    }

    @Test
    public void categoriesWithIdenticalParentAreEqual() throws Exception {
	LocalDateTime now = LocalDateTime.now();
	Category parent1 = createParentCategory(1, "Top", LocalDateTime.now());
	Category cat1 = createCategory(5, "Top", Boolean.TRUE, now, parent1);
	Category cat2 = createCategory(5, "Top", Boolean.TRUE, now, parent1);

	Assertions.assertThat(cat1).isEqualTo(cat2);
	assertThat(cat1.equals(cat2)).isTrue();
	assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
    }

    private Category createCategory(Integer id, String name, Boolean visible,
            LocalDateTime created, Category parent) {
	return new TestCategoryObject(id, name, null,
            visible, null, parent, created, null, 1);
    }
}
