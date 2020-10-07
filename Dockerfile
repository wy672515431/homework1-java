FROM openjdk:13
WORKDIR /app/
COPY ./* ./
RUN javac Test.java