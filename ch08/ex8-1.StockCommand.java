// 예제 8-1 StockCommand

public class StockCommand extends HystrixCommand<String> {
    private final String stockCode;

    public StockCommand(String stockCode) {
	super(HystrixCommandGroupKey.Factory.asKey("StockGroup"));
	this.stockCode = stockCode;
    }

    @Override
    protected String run() throws Exception {
	// 현재 주가를 가져오기 위해 HTTP 요청 실행
    }
}
