// 예제 8-7 스레드 설정이 있는 StockCommand

super(Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("StockGroup"))
    .andCommandPropertiesDefaults(
        HystrixCommandProperties.Setter()
	    .withCircuitBreakerRequestVolumeThreshold(10)
	    .withCircuitBreakerSleepWindowInMilliseconds(10000)
	    .withCircuitBreakerErrorThresholdPercentage(50)
    )
    .andThreadPoolPropertiesDefaults(
        HsytrixThreadPoolProperties.Setter()
	    .withCoreSize(1)
    )
);
