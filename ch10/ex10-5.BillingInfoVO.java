// 예제 10-5 BillingInfoVO

public class BillingInfoVO implements Serializable {

    private Long billingId = null;
    private Long orderId = null;
    
    private String name = null;
    private String address = null;
    private String address2 = null;
    private String city = null;
    private String state = null;
    private String zipCode = null;
    private String country = null;
    private String phone = null;
    private String email = null;

    private String cardToken = null;
    private String cardChargeId = null;

    ...

    public void setCardToken { String _cardToken ) { cardToken = _cardToken; }
    public String getCardToken () { return cardToken; }

    public void setCardChargeId ( String _cardChargeId ) { cardChargeId = _cardChargeId; }
    public String getCardChargeId () { return cardChargeId; }
}
