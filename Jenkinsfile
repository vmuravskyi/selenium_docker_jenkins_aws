pipeline {

    agent any

    when {
        branch 'master'
    }

    stages {

        stage('build jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('build image') {
            steps {
                sh 'docker build -t vmuravskyi/dokerized-tests:latest .'
            }
        }

        stage('push image') {
            environment {
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps {
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push vmuravskyi/dokerized-tests:latest'
                sh "docker tag vmuravskyi/dokerized-tests:latest vmuravskyi/dokerized-tests:${env.BUILD_NUMBER}"
                sh "docker push vmuravskyi/dokerized-tests:${env.BUILD_NUMBER}"
            }
        }

    }

    post {
        always {
            sh 'docker logout'
        }
    }

}
