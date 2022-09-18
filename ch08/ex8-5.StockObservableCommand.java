// 예제 8-5 폭백을 추가한 StockObservableCommand

public class StockObservableCommand extends HystrixObservaleCommand<String> {
    ...
    @Override
    protected Observable<String> resumeWithFallback() {
	// 네트워크 호출을 하지 않고 캐시해둔 이전 날짜 주가를 'Observable'로 변환
    }
}
