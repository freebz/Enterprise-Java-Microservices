// 예제 9-11 keycloak-service.js

import Keycloak from 'keycloak-js';

const keycloakAuth = Keycloak('/keycloak.json');
keycloakAuth.init({ onLoad: 'check-sso' })
  .success((authenticated) => {
    // 초기화가 성공한 경우 처리
  })
  .error(() => {
    // 초기화가 실패한 경우 처리
  });
