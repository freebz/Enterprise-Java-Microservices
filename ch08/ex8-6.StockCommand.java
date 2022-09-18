// 예제 8-6 요청 캐시를 사용하는 StockCommand

public class StockCommand extends HystrixCommand<String> {
    private final String stockCode;

    ...

    @Override
    protected String getCacheKey() {
	return this.stockCode;
    }
}
