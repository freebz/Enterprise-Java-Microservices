// 예제 10-6 PaymentService

@Path("/")
public interface PaymentService {
    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    PaymentResponse charge(PaymentRequest paymentRequest);
}
