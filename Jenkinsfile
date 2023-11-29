pipeline {
    agent any
    stages {
        stage('Variables de Entorno') {
            steps {
                sh 'cp /home/Jenkins/variables/application.properties /var/lib/jenkins/workspace/api-devops/src/main/resources/'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }
	stage('Create Docker and Up docker-compose') {
            steps {
                sh "docker-compose down"
                sh "docker-compose up -d"
            }
        }
    }
}