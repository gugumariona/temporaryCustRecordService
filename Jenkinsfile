pipeline {
    agent any
    tools {
        jdk 'jdk8'
        maven 'maven_3_6_3'
    }
    stages {
        stage ('Version'){
            steps {
                sh 'mvn --version'
            }
        }
        stage ('Dependency'){
            steps {
                sh 'mvn dependency:tree'
            }
        }
        stage ('Test') {
            steps {
               sh 'mvn test'
            }
        }
        stage ('Verify and LoadTest'){
            steps {
               sh 'mvn clean verify'
            }
         }
        stage ('Code Analysis'){
            steps {
                 sh 'mvn sonar:sonar -Dsonar.projectKey=client-profile-service -Dsonar.host.url=http://104.254.246.218:9090 -Dsonar.login=fcc67ce84edfafdefef2447b6a97311b3f4345ce'
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
