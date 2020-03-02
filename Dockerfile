#FROM oracle/graalvm-ce:19.3.1-java11 as graalvm
#FROM oracle/graalvm-ce:19.3.1-java8 as graalvm
FROM oracle/graalvm-ce:19.3.0-java11 as graalvm
#FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
#COPY . /home/app/echo
#WORKDIR /home/app/echo
#RUN gu install native-image
#RUN native-image --no-server --static -cp build/libs/echo-*-all.jar
COPY ./build/libs/echo-0.1-all.jar /home/app/echo/
EXPOSE 8080
WORKDIR /home/app/echo
ENTRYPOINT ["java", "-jar", "echo-0.1-all.jar", "-Dname=echo-server"]
#ENTRYPOINT ["/app/echo", "-Djava.library.path=/app", "-Dname=echo-server"]

#FROM frolvlad/alpine-glibc
#RUN yum install -y libstdc++-static
#EXPOSE 8080
#COPY --from=graalvm /home/app/echo/echo /app/echo
#ENTRYPOINT ["/app/echo", "-Djava.library.path=/app", "-Dname=echo-server"]
