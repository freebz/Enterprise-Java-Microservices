
// 예제 7-11 MessageResource

@Path("/")
public class PaymentServiceResource {

    private Topology topology;

    public PaymentServiceResource() {
	try {
	    topology = Topology.lookup();
	} catch (NamingException e) {
	    e.printStackTrace();
	}
    }

    @POST
    @Path("/sync")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeResponse chargeSync(ChargeRequest chargeRequest) throws Exception {
	ResteasyClient client = new ResteasyClientBuilder().build();
	URI url = getService("chapter7-stripe");
	ResteasyWebTarget target = client.target(url);
	StripeService stripe = target.proxy(StripeService.class);
	return stripe.charge(chargeRequest);
    }

    //...

    private URI getService(String name) throws Exception {
	Map<String, List<Topology, Entry>> map = this.topology.asMap();

	if (map.isEmpty()) {
	    throw new Exception("Service not found for '" + name + "'");
	}

	Optional<Topology.Entry> setOptional = map
	    .get(name)
	    .stream()
	    .findFirst();

	Topology.Entry serviceEntry =
	    setOptional.orElseThrow(
                () -> new Exception("Service not found for '" + name + "'")
            );

	return new URI("http", null, serviceEntry.getAddress(), serviceEntry.getPort(), null, null, null);
    }
}
