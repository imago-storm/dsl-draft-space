import java.security.MessageDigest
import groovy.json.JsonOutput


File path = new File('/opt/electriccloud/electriccommander/plugins/EC-Docker-1.5.0.0/lib')
int chunkSize = 2 * 1024 * 1024

String fileList = '''grapeConfig.xml
grapes/org.slf4j/slf4j-api/jars/slf4j-api-1.7.25.jar
grapes/org.slf4j/slf4j-api/ivy-1.7.25.xml.original
grapes/org.slf4j/slf4j-api/ivydata-1.7.5.properties
grapes/org.slf4j/slf4j-api/ivy-1.7.5.xml.original
grapes/org.slf4j/slf4j-api/ivydata-1.7.25.properties
grapes/org.slf4j/slf4j-api/ivy-1.7.5.xml
grapes/org.slf4j/slf4j-api/ivy-1.7.25.xml
grapes/org.jetbrains/annotations/jars/annotations-13.0.jar
grapes/org.jetbrains/annotations/ivydata-13.0.properties
grapes/org.jetbrains/annotations/ivy-13.0.xml.original
grapes/org.jetbrains/annotations/ivy-13.0.xml
grapes/org.objenesis/objenesis/jars/objenesis-2.6.jar
grapes/org.objenesis/objenesis/ivydata-2.6.properties
grapes/org.objenesis/objenesis/ivy-2.6.xml.original
grapes/org.objenesis/objenesis/ivy-2.6.xml
grapes/org.codehaus.groovy/groovy-json/jars/groovy-json-2.4.13.jar
grapes/org.codehaus.groovy/groovy-json/ivy-2.4.13.xml
grapes/org.codehaus.groovy/groovy-json/ivydata-2.4.13.properties
grapes/org.codehaus.groovy/groovy-json/ivy-2.4.13.xml.original
grapes/com.kohlschutter.junixsocket/junixsocket-common/jars/junixsocket-common-2.0.4.jar
grapes/com.kohlschutter.junixsocket/junixsocket-common/ivy-2.0.4.xml.original
grapes/com.kohlschutter.junixsocket/junixsocket-common/ivydata-2.0.4.properties
grapes/com.kohlschutter.junixsocket/junixsocket-common/ivy-2.0.4.xml
grapes/com.kohlschutter.junixsocket/junixsocket-native-common/jars/junixsocket-native-common-2.0.4.jar
grapes/com.kohlschutter.junixsocket/junixsocket-native-common/ivy-2.0.4.xml.original
grapes/com.kohlschutter.junixsocket/junixsocket-native-common/ivydata-2.0.4.properties
grapes/com.kohlschutter.junixsocket/junixsocket-native-common/ivy-2.0.4.xml
grapes/org.apache.commons/commons-compress/jars/commons-compress-1.15.jar
grapes/org.apache.commons/commons-compress/ivydata-1.15.properties
grapes/org.apache.commons/commons-compress/ivy-1.15.xml
grapes/org.apache.commons/commons-compress/ivy-1.15.xml.original
grapes/io.github.microutils/kotlin-logging/jars/kotlin-logging-1.4.8.jar
grapes/io.github.microutils/kotlin-logging/ivy-1.4.8.xml
grapes/io.github.microutils/kotlin-logging/ivydata-1.4.8.properties
grapes/io.github.microutils/kotlin-logging/ivy-1.4.8.xml.original
grapes/org.bouncycastle/bcprov-jdk15on/jars/bcprov-jdk15on-1.58.jar
grapes/org.bouncycastle/bcprov-jdk15on/ivy-1.58.xml
grapes/org.bouncycastle/bcprov-jdk15on/ivydata-1.58.properties
grapes/org.bouncycastle/bcprov-jdk15on/ivy-1.58.xml.original
grapes/org.bouncycastle/bcpkix-jdk15on/jars/bcpkix-jdk15on-1.58.jar
grapes/org.bouncycastle/bcpkix-jdk15on/ivy-1.58.xml
grapes/org.bouncycastle/bcpkix-jdk15on/ivydata-1.58.properties
grapes/org.bouncycastle/bcpkix-jdk15on/ivy-1.58.xml.original
grapes/com.google.re2j/re2j/jars/re2j-1.1.jar
grapes/com.google.re2j/re2j/ivy-1.1.xml.original
grapes/com.google.re2j/re2j/ivy-1.1.xml
grapes/com.google.re2j/re2j/ivydata-1.1.properties
grapes/com.squareup.okio/okio/jars/okio-1.13.0.jar
grapes/com.squareup.okio/okio/ivy-1.13.0.xml
grapes/com.squareup.okio/okio/ivy-1.13.0.xml.original
grapes/com.squareup.okio/okio/ivydata-1.13.0.properties
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre7/ivydata-1.2.10.properties
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre7/jars/kotlin-stdlib-jre7-1.2.10.jar
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre7/ivy-1.2.10.xml.original
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre7/ivy-1.2.10.xml
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre8/ivydata-1.2.10.properties
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre8/jars/kotlin-stdlib-jre8-1.2.10.jar
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre8/ivy-1.2.10.xml.original
grapes/org.jetbrains.kotlin/kotlin-stdlib-jre8/ivy-1.2.10.xml
grapes/org.jetbrains.kotlin/kotlin-reflect/ivydata-1.2.10.properties
grapes/org.jetbrains.kotlin/kotlin-reflect/jars/kotlin-reflect-1.2.10.jar
grapes/org.jetbrains.kotlin/kotlin-reflect/ivy-1.2.10.xml.original
grapes/org.jetbrains.kotlin/kotlin-reflect/ivy-1.2.10.xml
grapes/org.jetbrains.kotlin/kotlin-stdlib/ivydata-1.2.10.properties
grapes/org.jetbrains.kotlin/kotlin-stdlib/jars/kotlin-stdlib-1.2.10.jar
grapes/org.jetbrains.kotlin/kotlin-stdlib/ivy-1.2.10.xml.original
grapes/org.jetbrains.kotlin/kotlin-stdlib/ivy-1.2.10.xml
grapes/com.squareup.moshi/moshi-kotlin/jars/moshi-kotlin-1.5.0.jar
grapes/com.squareup.moshi/moshi-kotlin/ivy-1.5.0.xml.original
grapes/com.squareup.moshi/moshi-kotlin/ivydata-1.5.0.properties
grapes/com.squareup.moshi/moshi-kotlin/ivy-1.5.0.xml
grapes/com.squareup.moshi/moshi/jars/moshi-1.5.0.jar
grapes/com.squareup.moshi/moshi/ivy-1.5.0.xml.original
grapes/com.squareup.moshi/moshi/ivydata-1.5.0.properties
grapes/com.squareup.moshi/moshi/ivy-1.5.0.xml
grapes/com.squareup.okhttp3/okhttp/ivydata-3.9.1.properties
grapes/com.squareup.okhttp3/okhttp/jars/okhttp-3.9.1.jar
grapes/com.squareup.okhttp3/okhttp/ivy-3.9.1.xml
grapes/com.squareup.okhttp3/okhttp/ivy-3.9.1.xml.original
grapes/org.scijava/native-lib-loader/jars/native-lib-loader-2.0.2.jar
grapes/org.scijava/native-lib-loader/ivy-2.0.2.xml.original
grapes/org.scijava/native-lib-loader/ivydata-2.0.2.properties
grapes/org.scijava/native-lib-loader/ivy-2.0.2.xml
grapes/ch.qos.logback/logback-classic/jars/logback-classic-1.0.13.jar
grapes/ch.qos.logback/logback-classic/ivydata-1.0.13.properties
grapes/ch.qos.logback/logback-classic/ivy-1.0.13.xml.original
grapes/ch.qos.logback/logback-classic/ivy-1.0.13.xml
grapes/ch.qos.logback/logback-core/jars/logback-core-1.0.13.jar
grapes/ch.qos.logback/logback-core/ivydata-1.0.13.properties
grapes/ch.qos.logback/logback-core/ivy-1.0.13.xml.original
grapes/ch.qos.logback/logback-core/ivy-1.0.13.xml
grapes/de.gesellix/docker-filesocket/ivy-2017-12-18T18-53-05.xml.original
grapes/de.gesellix/docker-filesocket/jars/docker-filesocket-2017-12-18T18-53-05.jar
grapes/de.gesellix/docker-filesocket/ivydata-2017-12-18T18-53-05.properties
grapes/de.gesellix/docker-filesocket/ivy-2017-12-18T18-53-05.xml
grapes/de.gesellix/docker-client/jars/docker-client-2018-01-26T21-28-05.jar
grapes/de.gesellix/docker-client/ivy-2018-01-26T21-28-05.xml
grapes/de.gesellix/docker-client/ivydata-2018-01-26T21-28-05.properties
grapes/de.gesellix/docker-client/ivy-2018-01-26T21-28-05.xml.original
grapes/de.gesellix/docker-engine/jars/docker-engine-2018-01-22T23-17-39.jar
grapes/de.gesellix/docker-engine/ivydata-2018-01-22T23-17-39.properties
grapes/de.gesellix/docker-engine/ivy-2018-01-22T23-17-39.xml.original
grapes/de.gesellix/docker-engine/ivy-2018-01-22T23-17-39.xml
grapes/de.gesellix/docker-compose/ivy-2017-12-18T19-13-48.xml
grapes/de.gesellix/docker-compose/jars/docker-compose-2017-12-18T19-13-48.jar
grapes/de.gesellix/docker-compose/ivydata-2017-12-18T19-13-48.properties
grapes/de.gesellix/docker-compose/ivy-2017-12-18T19-13-48.xml.original
grapes/org.yaml/snakeyaml/ivy-1.19.xml
grapes/org.yaml/snakeyaml/ivy-1.19.xml.original
grapes/org.yaml/snakeyaml/ivydata-1.19.properties
grapes/org.yaml/snakeyaml/bundles/snakeyaml-1.19.jar'''


int startWithFile = 0
List<String> chunk = []
int size = 0
List<String> files = fileList.split("\n")
int i = startWithFile
while(size < chunkSize) {
    String fileName = files[i]
    File f = new File(path, fileName)
    if (f.exists()) {
        byte[] b = f.bytes
        size += b.size()
        chunk.add(b.encodeBase64().toString())
    }
    else {
        throw new RuntimeException("File $fileName does not exist")
    }
}

return [
    chunk: chunk
]

// Older version of Groovy does not have String.md5()
String generateMD5(byte[] bytes) {
    MessageDigest digest = MessageDigest.getInstance("MD5")
    digest.update(bytes)
    byte[] md5sum = digest.digest()
    BigInteger bigInt = new BigInteger(1, md5sum)
    return bigInt.toString(16).padLeft(32, '0')
}
