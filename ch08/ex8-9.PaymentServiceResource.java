// 예제 8-9 PaymentServiceResource

@Path("/")
@ApplicationScoped
public class PaymentServiceResource {
    ...

    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public PaymentResponse chargeSync(PaymentRequest paymentRequest) throws Exception {
	Payment payment = setupPayment(paymentRequest);
	ChargeResponse response = new ChargeResponse();

	try {
	    URI url = getService("chapter8-stripe");
	    StripeCommand stripeCommand = new StripeCommand(
                url,
		paymentRequest.getStripeRequest(),
		HystrixCommandProperties.Setter()
		.withExecutionIsolationStrategy(
                    HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
                )
		.withExecutionIsolationSemaphoreMaxConcurrentRequests(1)
		.withCircuitBreakerRequestVolumeThreshold(5)
            );
	    response = stripeCommand.execute();
	    payment.chargeId(response.getChargeId());
	} catch (Exception e) {
	    payment.chargeStatus(ChargeStatus.FAILED);
	}

	em.persist(payment);
	return PaymentResponse.newInstance(payment, response);
    }

    @POST
    @Path("/async")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void chargeAsync(@Suspended final AsyncResponse asyncResponse,
			    PaymentRequest paymentRequest) throws Exception {
	Payment payment = setupPayment(paymentRequest);

	URI url = getService("chapter8-stripe");
	StripeCommand stripeCommand =
	    new StripeCommand(url, paymentRequest.getStripeRequest());

	stripeCommand
	    .toObservable()
	    .subscribe(
                (result) -> {
		    payment.chargeid(result.getChargeId());
		    storePayment(payment);
		    asyncResponse.resume(PaymentResponse.newInstance(payment, result));
		},
		(error) -> {
		    payment.chargeStatus(ChargeStatus.FAILED);
		    storePayment(payment);
		    asyncResponse.resume(error);
		}
        );
    }
    ...
}
