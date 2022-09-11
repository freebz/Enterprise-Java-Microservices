// 예제 3-2 CartController

public class CartController {
    private static List<CartItem> items = new ArrayList<>();

    static {
	items.add(new CartItem("sunscreen", 3));
	items.add(new CartItem("towel", 1));
	items.add(new CartItem("hat", 5));
	items.add(new CartItem("umbrella", 1));
    }

    public List<CartItem> all() throws Exception {
	return items;
    }

    public String addOrUpdateItem(String itemName, Integer qty) throws Exception {
	Optional<CartItem> item = items.stream()
	    .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
	    .findFirst();

	if (item.isPresent()) {
	    Integer total = item.get().increaseQuantity(qty).getItemQuantity();
	    return "Updated quantity of '" + itemName + "' to " + total;
	}

	items.add(new CartItem(itemName, qty));
	return "Added '" + itemName + "' to shopping cart";
    }

    public CartItem getItem(String itemName) throws Exception {
	return items.stream()
	    .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
	    .findFirst()
	    .get();
    }
}
