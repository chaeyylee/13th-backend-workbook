# amazoncorretto:17-alpine: AWS가 관리하는 Java 17 경량 이미지예요
# alpine은 용량이 매우 작은 리눅스 배포판으로, 이미지 크기를 줄여줘요
FROM amazoncorretto:17-alpine

# 컨테이너 안에서 작업할 기본 디렉토리를 /app으로 설정해요
WORKDIR /app

# 로컬에서 빌드한 .jar 파일을 컨테이너 안의 /app/app.jar로 복사해요
# *.jar 와일드카드를 쓰면 파일 이름이 바뀌어도 유연하게 대응할 수 있어요
COPY build/libs/*.jar app.jar

# 컨테이너가 시작될 때 실행할 명령어예요
# -Dspring.profiles.active=prod: prod profile로 실행해서 application-prod.yml을 읽어요
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]