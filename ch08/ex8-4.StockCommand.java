// 예제 8-4 폴백을 추가한 StockCommand

public class StockCommand extends HystrixCommand<String> {
    ...
    @Override
    protected String getFallback() {
	// 네트워크 호출을 하지 않고 캐시해둔 이전 날짜 주가를 반환
    }
}
