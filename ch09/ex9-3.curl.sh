# 예제 9-3 스트라이프에 대한 curl의 출력

$ curl -v http://localhost:8080/stripe/charge
* Tyring ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /stripe/charge HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 401 Unauthorized
< Expires: 0
< Connection: keep-alive
< WWW-Authenticate: Bearer realm="cayambe"
< Cache-Control: no-cache, no-store, must-revalidate
< Pragma: no-cache
< Content-Type: text/html;charset=UTF-8
< Content-Length: 71
< Date: Sun, 25 Feb 2018 03:22:53 GMT
<
* Connection #0 to host localhost left intact
<html><head><title>Error</title</head><body>Unauthorized</body></html>
