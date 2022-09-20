// 예제 9-6 StripeCommand의 run 메서드

protected ChargeResponse run() throws Exception {
    ResteasyClient client = new ResteasyClientBuilder().build();

    client.register((ClientRequestFilter) clientRequestContext -> {
	List<Object> list = new ArrayList<>();
	list.add("Bearer " + getAuthzClient().obtainAccessToken().getToken());
	clientRequestContext.getHeaders().put(HttpHeaders.AUTHORIZATION, list);
    });

    ResteasyWebTarget target = client.target(serviceURI);

    StripeService stripeService = target.proxy(StripeService.class);
    return stripeService.charge(chargeRequest);
}
