FROM openjdk:17-jdk-slim

# Versão do H2
ENV H2_VERSION=2.2.224

# Diretório de trabalho
WORKDIR /opt/h2

# Instala wget, baixa o JAR do H2 e limpa cache em uma única camada
RUN apt-get update && \
    apt-get install -y --no-install-recommends wget && \
    wget https://repo1.maven.org/maven2/com/h2database/h2/${H2_VERSION}/h2-${H2_VERSION}.jar -O h2.jar && \
    apt-get remove --purge -y wget && \
    apt-get clean && rm -rf /var/lib/apt/lists/*



# Expõe as portas do console Web e do servidor TCP
EXPOSE 9092 1521

# Inicia o H2 com argumento JSON-style (boa prática para containers)
CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-tcp", "-tcpAllowOthers", "-tcpPort", "1521", "-web", "-webAllowOthers", "-webPort", "9092", "-baseDir", "/opt/h2-data"]