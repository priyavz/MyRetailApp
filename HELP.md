This is a spring boot microservice that takes care of GET requests. Dockerizing this can enable us to scale this service separately.

Launch the application by Starting the MyRetailGetApplication.java or by the docker command  docker build -t "com/myretail". The docker configuration is mentioned in the DockerFile. This app uses common libraries from myRetailCommon repo.

The tests are present inside the test/java/com/myretail folder. All tests except WebClientServiceImplTest work. 

TODO: 
1)Fix WebClientServiceImplTest and get it to work and add more test cases. (Given some more time , this can easily be done).
2)EnableCaching at the app server layer by using ehcache/redis so that the performance can be optimized(This too can be easily done given a bit more time).

Successful product lookup :
http://localhost:8082/myRetail/v1/product/13860428
Refer: Succesful_GET_response.png

ProductNotFound:
http://localhost:8082/myRetail/v1/product/13860400
Refer: ProductNotFound_GET_Response.png

