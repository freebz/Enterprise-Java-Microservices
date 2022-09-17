// 예제 7-10 StripeService

@Path("/stripe")
public interface StripeService {

    @POST
    @Path("/charge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ChargeResource charge(ChargeRequest chargeRequest);

}
