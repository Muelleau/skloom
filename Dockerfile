
FROM --platform=linux/amd64 ubuntu:22.10

WORKDIR /opt

# dependencies
RUN apt update
RUN apt-get -y install wget
RUN apt-get install curl gnupg2 -y

# install java loom
RUN wget https://download.java.net/java/early_access/loom/6/openjdk-19-loom+6-625_linux-x64_bin.tar.gz
RUN tar -xvzf openjdk-19-loom+6-625_linux-x64_bin.tar.gz
ENV JAVA_HOME /opt/jdk-19
RUN export PATH="/opt/jdk-19/bin:$PATH"

# install sbt - https://www.scala-sbt.org/download.html
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add
RUN apt-get update && apt-get install sbt -y

#WORKDIR /build
#ADD . /build
#RUN sbt assembly



ENTRYPOINT ["tail", "-f", "/dev/null"]

