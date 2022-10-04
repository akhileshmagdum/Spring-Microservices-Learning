start /d D:\personal-git\Spring-Microservices-Learning\config-server mvn spring-boot:run
TIMEOUT /T 15
start /d D:\personal-git\Spring-Microservices-Learning\discovery-service mvn spring-boot:run
TIMEOUT /T 15
start /d D:\personal-git\Spring-Microservices-Learning\spring-cloud-api-gateway mvn spring-boot:run
TIMEOUT /T 15
start /d D:\personal-git\Spring-Microservices-Learning\user-app mvn spring-boot:run
TIMEOUT /T 15
start /d D:\personal-git\Spring-Microservices-Learning\album-app mvn spring-boot:run
TIMEOUT /T 15
start /d D:\personal-git\Spring-Microservices-Learning\cart-app mvn spring-boot:run
