FROM node as build_vueapp

COPY orbag-ui/ /src

WORKDIR /src

RUN npm ci

RUN npm run build

FROM maven as build_java

COPY . /src

RUN rm -fr /src/orbag-server/src/main/resources/public

COPY  --from=build_vueapp /src/dist /src/orbag-server/src/main/resources/public

WORKDIR /src

RUN mvn clean package

FROM  openjdk:17-oraclelinux8

COPY  --from=build_java /src/orbag-server/target/*.jar /app.jar

EXPOSE 8080/tcp

CMD ["java", "-jar", "/app.jar"]
