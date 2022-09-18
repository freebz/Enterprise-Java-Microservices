// 예제 8-3 SEMAPHORE를 사용한 StockCommand

public class StockCommand extends HystrixCommand<String> {
    private final String stockCode;

    public StockCommand(String stockCode) {
	super(Setter
	      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StockGroup"))
	      .andCommandPropertiesDefaults(
                  HystrixCommandProperties.Setter()
		      .withExecutionIsolationStrategy(
                          HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
                      )
                  )
	      );
	this.stockCode = stockCode;
    }
    ...
}
