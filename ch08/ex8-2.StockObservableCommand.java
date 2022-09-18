// 예제 8-2 StockObservableCommand

public class StockObservableCommand extends HystrixObservableCommand<String> {
    private final String stockCode;
    public StockObservableCommand(String stockCode) {
	super(HystrixCommandGroupKey.Factory.asKey("stockGroup"));
	this.stockCode = stockCode;
    }
    @Override
    protected Observable<String> construct() {
	// HTTP 요청을 실행하는 'Observable'을 반환
    }
}
