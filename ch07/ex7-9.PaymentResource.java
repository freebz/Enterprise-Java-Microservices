// 예제 7-9 PaymentResource

@Path("/")
public class PaymentServiceResource {
    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeResponse chargeSync(ChargeRequest chargeRequest) {
	ByteBuf buf = StripeService.INSTANCE.charge(chargeRequest).execute();
	return extractResult(buf);
    }
    @POST
    @Path("/async")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void chargeAsync(@Suspended final AsyncResponse asyncResponse,
	    ChargeRequest chargeRequest)
	    throws Exception {
	executorService().submit(() -> {
	    Observable<ByteBuf> obs =
		StripeService.INSTANCE.charge(chargeRequest).toObservable();
	    obs.subscribe(
                (result) -> {
		    asyncResponse.resume(extractResult(result));
		},
		asyncResponse::resume
	    );
	});
    }
    private ChargeResponse extractResult(ByteBuf result) {
	byte[] bytes = new byte[result.readableBytes()];
	result.readBytes(bytes);
	try {
	    return new ObjectMapper()
		.readValue(bytes, ChargeResponse.class);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
