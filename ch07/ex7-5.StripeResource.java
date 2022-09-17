// 예제 7-5 StripeResource

@Path("/")
@ApplicationScoped
@Adverties("chapter7-stripe")
public class StripeResource {

    @Inject
    @ConfigurationValue("stripe.key")
    private String stripeKey;

    @POST
    @Path("/charge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeResponse submitCharge(ChargeRequest chargeRequest) {
	Stripe.apiKey = this.stripeKey;

	Map<String, Object> chargeParams = new Hashmap<>();
	chargeParams.put("amount", chargeRequest.getAmount());
	chargeParams.put("currency", "usd");
	chargeParams.put("description", chargeRequest.getDescription());
	chargeParams.put("source", chargeRequest.getCardToken());

	Charge charge = Charge.create(chargeParams);

	return new ChargeResponse()
	    .chargeId(charge.getId())
	    .amount(charge.getAmount());
    }
}
