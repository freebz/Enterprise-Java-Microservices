// 예제 3-10 스프링 부트를 사용한 CartController

@RestController
public class CartController {
    @RequestMapping(
        method = RequestMethod.GET,
	path = "/",
	produces = "application/json")
    public List<CartItem> all() throws Exception {}

    @RequestMapping(
        method = RequestMethod.GET,
	path = "/add",
	produces = "application/json")
    public String addOrUpdateItem(
        @RequestParam("item") String itemName,
	@RequestParam("qty") Integer qty) throws Exception {
    }

    @RequestMapping(
        method = RequestMethod.GET,
	path = "/get/{itemName}",
	produces = "application/json")
    public CartItem getItem(
        @PathVariable("itemName") String itemName)
	throws Exception {
    }
}
