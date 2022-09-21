<!-- 예제 10-8 CheckOutForm.jsp -->

...
<script src="https://js.stripe.com/v3/"></script>
<script type="text/javascript">
  var stripe = Stripe({STRIPE_PUBLISH_KEY});
  var elements = stripe.elements();
...
  var card = elements.create('card', {style: style});

  function stripeTokenHandler(token) {
    // 서버에 제출하기 위해 토큰 ID를 폼에 삽입
    var cardToken = document.getElementById('cardToken');
    cardToken.value = token.id;

    // 폼 제출
    document.getElementById('orderForm').submit();
  };

  document.body.onload = function() {
    card.mount('#card-element');
    
    card.addEventListener('change', function(event) {
      var displayError = document.getElementById('card-errors');
      if (event.error) {
	displayError.textContent = event.error.message;
      } else {
	displayError.textContent = '';
      }
    });

    var form = document.getElementById('orderForm');
    form.addEventListener('submit', function(event) {
      event.preventDefault();
      
      stripe.createToken(card).then(function(result) {
	if (result.error) {
	  // 오류가 있으면 사용자에게 알려줌
	  var errorElement = document.getElementById('card-errors');
	  errorElement.textContent = result.error.message;
	} else {
	  stripeTokenHandler(result.token);
	}
      });
    });
  };
</script>

<form:form name="OrderForm" styleId="orderForm"
  type="org.cayambe.web.form.OrderActionForm"
  action="SubmitOrder.do" scope="request">
...
  <tr>
    <th align="right">
      <label for="card-element">
	Enter card details
      </label>
    </th>
    <td align="left">
      <div id="card-element">
	<!-- 스트라이프 엘리먼트를 여기 삽입 -->
      </div>

      <!-- 폼 오류를 출력하기 위해 사용 -->
      <div id="card-errors" role="alert"></div>
      <form:hidden property="cardToken" styleId="cardToken"/>
    </td>
  </tr>
  ...
</from:form>
