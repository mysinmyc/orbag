FROM node as build_vueapp
COPY ui/orbag_ui_vue/ /src
WORKDIR /src
RUN npm ci
RUN npm run build

FROM mysinmyc/flutter as build_flutter
COPY --chown=1000:1000 ui/orbag_ui_flutter/ /home/build/src
RUN rm /home/build/src/web/public/config.json
RUN flutter pub get -d chrome
RUN flutter build web  --base-href /ui/flutter/

FROM maven as build_java
COPY . /src
RUN rm -fr /src/orbag-server/src/main/resources/public/ui/
COPY  --from=build_vueapp /src/dist /src/orbag-server/src/main/resources/public/ui/vue
COPY  --from=build_flutter /home/build/src/build/web /src/orbag-server/src/main/resources/public/ui/flutter
WORKDIR /src
RUN mvn -DskipTests=true clean package 

FROM  openjdk:17-oraclelinux8
COPY  --from=build_java /src/orbag-server/target/*.jar /app.jar
EXPOSE 8080/tcp
CMD ["java", "-jar", "/app.jar"]
