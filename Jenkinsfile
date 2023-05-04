pipeline {
    agent any

    tools {
        maven "jenkins-maven"
    }

    stages{

        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sachithariyathilaka/Car-Sale-App']])
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Image'){
            steps{
                script{
                    bat 'docker pull mysql:5.7'
                    bat 'docker run --name mysqldb --network springboot-mysql-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=carsale -d mysql:5.7'
                    bat 'docker build -t carsale-backend .'
                }
            }
        }

        stage('Run Application'){
             steps{
                script{
                    bat 'docker run --network springboot-mysql-network --name carsale-container -p 8081:8081 -d carsale-backend'
                }
            }
        }
    }
}
