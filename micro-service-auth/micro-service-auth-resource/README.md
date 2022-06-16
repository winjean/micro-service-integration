1. 授权码（认证码）模式 （Authorization code) response_type=code
获取 AUTHORIZATION_CODE
get http://localhost:8401/micro-service-auth/oauth/authorize?response_type=code&client_id=zuul&redirect_uri=http://localhost:8401/micro-service-auth/authorization/code

获取access_token、token_type、refresh_token、expires_in、scope、jti
post http://localhost:8401/micro-service-auth/oauth/token?client_id=zuul&client_secret=123456&grant_type=authorization_code&redirect_uri=http://localhost:8401/micro-service-auth/api/test&code=pDI482

2. 简化（隐形）模式 (Impilict） response_type=token
获取access_token、token_type、expires_in、scope、jti
access_token直接在前端获取不安全
http://localhost:8401/micro-service-auth/oauth/authorize?response_type=token&client_id=zuul&redirect_uloginri=http://localhost:8401/micro-service-auth/api/test

3. 用户名密码模式 (Resource Owner Password Credential) grant_type=password
获取access_token、token_type、refresh_token、expires_in、scope、jti
post http://localhost:8401/micro-service-auth/oauth/token?grant_type=password&username=admin&password=admin&client_id=zuul&client_secret=123456

4. 客户端模式 (Client Credential) grant_type=client_credential
获取access_token、token_type、expires_in、scope、jti
post http://localhost:8401/micro-service-auth/oauth/token?grant_type=client_credentials&client_id=zuul&client_secret=123456


client_id、client_secret、redirect_uri在AuthorizationServerConfigurer中定义
