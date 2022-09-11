// 예제 3-7 JAXB 매핑을 제공하는 CartItem

@XmlRootElement
public class CartItem {
    public CartItem() {
    }
    ...
    public CartItem setItemName(String itemName) {
	this.itemName = itemName;
	return this;
    }
    ...
    public CartItem setItemQuantity(Integer itemQuantity) {
	this.itemQuantity = itemQuantity;
	return this;
    }
}
