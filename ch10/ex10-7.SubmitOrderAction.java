// 예제 10-7 SubmitOrderAction

public class SubmitOrderAction extends Action
{
    public ActionForward perform( ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response )
	throws IOException, ServiceException
    {
	//...

	OrderActionForm oaf = (OrderActionForm)form;

	try {
	    delegate = new CheckOutDelegate();
	    OrderVO orderVO = new OrderVO();
	    orderVO = (OrderVO)oaf.toOrderVO();
	    orderVO.setCartVO( (CartVO) session.getAttribute("Cart") );

	    // 지불 서비스 호출
	    ResteasyClient client = new ResteasyClientBuilder().build();
	    ResteasyWebTarget target =
		client.target("http://cayambe-payment-service-myproject.192.168.64.33.nip.io");
	    PaymentService paymentService = target.proxy(PaymentService.class);
	    PaymentResponse paymentResponse =
		paymentService.charge(new PaymentRequest()
				      .amount((long) (orderVO.getCartVO().getTotalCost() * 100))
				      .cardToken(oaf.getCardToken())
				      .orderId(Math.toIntExact(orderVO().getOrderId()))
	    );

	    orderVO.getBillingInfoVO().setCardChargeId(paymentResponse.getChargeId());
	    delegate.Save ( orderVO );

	    CartDelegate cartDelegate = new CartDelegate();
	    cartDelegate.Remove( orderVO.getCartVO() );
	} catch(Exception e) {
	    forwardMapping = CayambeActionMappings.FAILURE;
	    errors.add( ActionErrors.GLOBAL_ERROR, new ActionError("error.cart.UpdatedCartError") );
	}
	return mapping.findForward( forwardMapping );
    }
}
