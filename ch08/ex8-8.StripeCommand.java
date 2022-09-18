// 예제 8-8 StripeCommand

public class StripeCommand extends HystrixCommand<ChargeResponse> {
    private URI serviceURI;

    private final ChargeRequest chargeRequest;

    public StripeCommand(URI serviceURI, ChargeRequest chargeRequest) {
	super(Setter
	        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StripeGroup"))
	        .andCommandPropertiesDefaults(
                    HystrixCommandProperties.Setter()
		        .withCircuitBreakerRequestVolumeThreshold(10)
		        .withCircuitBreakerSleepWindowInMilliseconds(10000)
		        .withCircuitBreakerErrorThresholdPercentage(50)
            )
	);

	this.serviceURI = serviceURI;
	this.chargeRequest = chargeRequest;
    }

    public StripeCommand(URI serviceURI,
            Chargerequest chargeRequest, HystrixCommandProperties.Setter commandProperties) {
	suepr(Setter
	    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StripeGroup"))
	    .andCommandPropertiesDefaults(commandPropoerties)
	);
	this.serviceURI = serviceURI;
	this.chargeRequest = chargeRequest;
    }

    @Override
    protected ChargeResponse run() throws Exception {
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget target = client.target(serviceURI);

	StripeService stripeService = target.proxy(StripeService.class);
	return stripeService.charge(chargeRequest);
    }

    @Override
    protected ChargeResponse getFallback() {
	return new ChargeResponse();
    }
}
