// 예제 7-7 StripeService

@ResourceGroup(name = "chapter7-stripe")
public interface StripeService {
    StripeService INSTANCE = Ribbon.from(StripeService.class);

    @TemplateName("charge")
    @Http(
	  method = Http.HttpMethod.POST,
	  uri = "/stripe/charge",
	  headers = {
	      @Http.Header(
                  name = "Content-Type",
		  value = "application/json"
              )
	  }
    )
    @ContentTransformerClass(ChargeTransformer.class)
    RibbonRequest<BtyeBuf> charge(@Context ChargeRequest chargeRequest);
}
