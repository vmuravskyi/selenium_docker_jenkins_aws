pipeline {

    agent any

    stages {

        stage('build jar') {
            steps {
                sh "mvn clean package -DskipTests=true"
            }
        }

        stage('build docker image') {
            steps {
                sh "docker build -t=muravskyi/selenium-automation ."
            }
        }

        stage('push docker image to docker-hub') {
            steps {
                sh "docker push muravskyi/selenium-automation"
            }
        }

    }

    post {
        always {
            echo "doing clean up"
        }
    }

}