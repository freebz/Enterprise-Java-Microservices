// 예제 10-4 OrderDAO

public class OrderDAO {
    public void Save(OrderVo orderVO)
    {
    ...
	StringBuffer sqlBillingInfo = new StringBuffer(512);
	sqlBillingInfo.append("insert into billing_info ");
	sqlBillingInfo.append("(order_id,name,address1,address2,city,state,zipcode,country,name_on_card,");
	sqlBillingInfo.append("card_charge_id,phone,email) ");
	sqlBillingInfo.append("values ('");
	sqlBillingInfo.append(orderId);
	sqlBillingInfo.append("','");
    ...
	sqlBillingInfo.append(orderVO.getBillingInfoVO().getCountry());
	sqlBillingInfo.append("','");
	sqlBillingInfo.append(orderVO.getBillingInfoVO().getCardChargeId());
	sqlBillingInfo.append("','");
    ...
    }

    ...

    public OrderVO getOrderVO( OrderVO orderVO )
    {
    ...
	b.setCardChargeId( rs.getString("billing_info.card_charge_id") );
    ...
    }
}
