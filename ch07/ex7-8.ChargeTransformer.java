// 예제 7-8 ChargeTransformer

public class ChargeTransformer implements ContentTransformer<ChargeRequest> {
    @Override
    public ByteBuf call(ChargeRequest chargeRequest, ByteBufAllocator byteBufAllocator) {
	try {
	    byte[] bytes = new ObjectMapper().writeValueAsBytes(chargeRequest);
	    ByteBuf byteBuf = byteBufAllocator.buffer(bytes.length);
	    byteBuf.writeBytes(bytes);
	    return byteBuf;
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
