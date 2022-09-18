// 예제 8-11 StripeServiceFallbackHandler

public class StripeServiceFallbackHandler implements FallbackHandler<ByteBuf> {
    @Override
    public Observable<ByteBuf> getFallback(
        HystrixInvokableInfo<?> hystrixInfo,
	Map<String, Object> requestProperties) {

	ChargeResponse response = new ChargeResponse();
	byte[] bytes = new byte[0];
	try {
	    bytes = new ObjectMapper().writeValueAsBytes(response);
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}
	ByteBuf byteBuf =
	    UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length);
	byteBuf.writeBytes(bytes);
	return Observable.just(byteBuf);
    }
}
