// 예제 9-12 카테고리 삭제

import axios from 'axios';
const ROOT_URL = 'http://localhost:8081';

if (store.getState().securityState.authenticated) {
  store.getState().securityState.keycloak.getToken()
    .then(token => {
      axios.delete(`${ROOT_URL}/admin/category/${id}`, {
	headers: {
	  'Authorization': 'Bearer ' + token
	}
      })
	.then(response => {
	  // 성공 응답 처리
	})
	.catch(error => {
	  // 오류 처리
	});
    })
    .catch(error => {
      dispatch(notifyError("Error updating token", error));
    });

} else {
  dispatch(notifyError("User is not authenticated", ""));
}
