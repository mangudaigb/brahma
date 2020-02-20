FROM oracle/graalvm-ce:19.3.1-java11 as graalvm
#FROM oracle/graalvm-ce:19.3.0-java11 as graalvm # For JDK 11
COPY . /home/app/echo
WORKDIR /home/app/echo
RUN gu install native-image
RUN native-image --no-server -cp build/libs/echo-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/echo/echo /app/echo
ENTRYPOINT ["/app/echo", "-Djava.library.path=/app", "-Dname=echo-server"]
