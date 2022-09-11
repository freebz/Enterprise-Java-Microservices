package ejm.shopping;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    private static List<CartItem> items = new ArrayList<>();

    static {
        items.add(new CartItem("sunscreen", 3));
        items.add(new CartItem("towel", 1));
        items.add(new CartItem("hat", 5));
        items.add(new CartItem("umbrella", 1));
    }

    @RequestMapping(
        method = RequestMethod.GET,
	    path = "/",
	    produces = "application/json")
    public List<CartItem> all() throws Exception {
        return items;
    }

    @RequestMapping(
        method = RequestMethod.GET,
	    path = "/add",
	    produces = "application/json")
    public String addOrUpdateItem(
            @RequestParam("item") String itemName,
            @RequestParam("qty") Integer qty) throws Exception {
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

    @RequestMapping(
        method = RequestMethod.GET,
	    path = "/get/{itemName}",
	    produces = "application/json")
    public CartItem getItem(
        @PathVariable("itemName") String itemName)
	throws Exception {
        return items.stream()
                .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
                .findFirst()
                .get();
    }
}
