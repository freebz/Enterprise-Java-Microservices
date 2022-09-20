// 예제 9-5 StripeCommand의 getAuthzClient 메서드

private AuthzClient getAuthzClient() {
    if (this.authzClient == null) {
	try {
	    this.authzClient = AuthzClient.create();
	} catch (Exception e) {
	    throw new RuntimeException("Could not create authorization client.", e);
	}
    }
    return this.authzclient;
}
