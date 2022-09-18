// 예제 8-10 StripeService

@ResourceGroup(name = "chapter8-stripe")
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
    @Hystrix(
        fallbackHandler = StripeServiceFallbackHandler.class
    )
    @ContentTransformerClass(ChargeTransformer.class)
    RibbonRequest<ByteBuf> charge(@Content ChargeRequest chargeRequest);
}
