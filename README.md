Sure! Below is a structured README file based on your requirements, including detailed explanations for each dependency in your Maven pom.xml, and properly formatted:

---

# Alias Android Appium Automation Project

Date: 10/21/2024

## Setup for This Project

### 1. Java, JDK 11 and Above
- Download JDK 11 or above and run as administrator. Extract the JDK path, for example:
  
  C:\Program Files\Java\java-11-openjdk-11.0.24.0.8-1
  
- Set the above path to environment variable JAVA_HOME:
  - Variable name: JAVA_HOME
  - Variable value: C:\Program Files\RedHat\java-11-openjdk-11.0.24.0.8-1

- Add JDK bin path to the environment variable Path:
  
  C:\Program Files\RedHat\java-11-openjdk-11.0.23-2\bin
  

### 2. Eclipse or IntelliJ IDE
- Install Eclipse or IntelliJ IDE from their official website.

### 3. Maven
- Download Maven from the official website and set the environment variable:
  - Variable name: MAVEN_HOME
  - Variable value: C:\Users\manoj.b\Downloads\apache-maven-3.9.9-bin\apache-maven-3.9.9

### 4. Git
- Download Git from the official website, install it, and set the Git user name and password.
- Ensure the Git cmd path is added to the environment variable Path:
  
  C:\Users\manoj.b\AppData\Local\Programs\Git\cmd
  

### 5. Appium and Android Studio
- Download Android Studio for the emulator and set the paths to environment variables:
  - Variable name: ANDROID_HOME
  - Variable value: C:\Users\manoj.b\AppData\Local\Android\Sdk

  - Variable name: ANDROID_SDK_ROOT
  - Variable value: C:\Users\manoj.b\AppData\Local\Android\Sdk

- Add the following paths to the environment variable Path:
  
  C:\Users\manoj.b\AppData\Local\Android\Sdk
  C:\Users\manoj.b\AppData\Local\Android\Sdk\platform-tools
  C:\Users\manoj.b\AppData\Local\Android\Sdk\emulator
  

### 6. Node.js
- Download Node.js from the official website and set the environment variable:
  - Variable name: NODE_HOME
  - Variable value: C:\Program Files\nodejs

- Add the following paths to the environment variable Path:
  
  C:\Program Files\nodejs\node_modules\npm\bin
  C:\Program Files\nodejs
  C:\Users\manoj.b\AppData\Roaming\npm
  

### 7. Jenkins
- Download the jenkins.war file from the official website and run it using the following command from the war file directory:
  sh
  java -jar jenkins.war
  
- Open Jenkins in the browser:
  
  http://localhost:8080/
  
- Add the access key from C:\Users\manoj.b\.jenkins\secrets/password. Copy and paste the access key. Add username, password, and email ID.
- Create a new Pipeline project and copy the source files and Jenkinsfile.
- Save the project and click on Build Now.

---

## Maven Project Configuration

### 1. Project Object Model (POM)
This project is a Maven project, and the dependencies required are defined in the pom.xml file:

xml
<project xmlns=http://maven.apache.org/POM/4.0.0
         xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance
         xsi:schemaLocation=http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd>
    <modelVersion>4.0.0</modelVersion>

    <groupId>alias</groupId>
    <artifactId>alias</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>alias</name>
    <url>http://maven.apache.org</url>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.source>1.8</maven.compiler.source>
        <extentreports-version>5.1.1</extentreports-version>
    </properties>

    <dependencies>
        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.17.0</version>
        </dependency>
        <!-- Appium -->
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>9.2.0</version>
        </dependency>
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.9.0</version>
        </dependency>
        <!-- Apache POI for Excel file handling -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.2</version>
        </dependency>
        <!-- ExtentReports for reporting -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>${extentreports-version}</version>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/java/com/qa/opencart/listeners</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <!-- Maven Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <!-- Maven Surefire Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>
                            src/test/resource/testRunners/testng_regressions.xml
                        </suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>


### 2. Dependencies Explained

#### Selenium
- Group ID: org.seleniumhq.selenium
- Artifact ID: selenium-java
- Version: 4.17.0
- Usage: Selenium is used for browser automation. It allows interaction with web elements and facilitates the execution of automated tests on web applications.

#### Appium
- Group ID: io.appium
- Artifact ID: java-client
- Version: 9.2.0
- Usage: Appium is used for automating mobile applications. This dependency facilitates interaction with mobile elements and the execution of automated tests on mobile applications.

#### TestNG
- Group ID: org.testng
- Artifact ID: testng
- Version: 7.9.0
- Usage: TestNG is a testing framework inspired by JUnit and NUnit. It is used to design and organize tests, generate reports, and provide annotations for test methods.

#### Apache POI
- Group ID: org.apache.poi
- Artifact IDs: poi-ooxml, poi
- Version: 5.2.2
- Usage: Apache POI is used for reading and writing Microsoft Excel files in Java. poi-ooxml handles the newer XML-based file format (.xlsx), while poi handles the older binary format (.xls).

#### ExtentReports
- Group ID: com.aventstack
- Artifact ID: extentreports
- Version: ${extentreports-version} (5.1.1 specified in properties)
- Usage: ExtentReports is used to generate detailed and visually attractive test reports. It helps track the execution of test cases and log their results.

---

## Contact Information

If you find any doubts or issues, please email us:
- Manoj: [manojbalasubramaniyam4488@gmail.com](mailto:manojbalasubramaniyam4488@gmail.com)
- Dhanush: [dhanushsekar004@gmail.com](mailto:dhanushsekar004@gmail.com)
- Dhanush: [praveen.mohansundar9@gmail.com](mailto:praveen.mohansundar9@gmail.com)

## Author

Manoj, Dhanush And Praveen

---

Thank you for using the Alias Android Appium Automation Project!
