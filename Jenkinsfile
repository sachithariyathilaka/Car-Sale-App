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
                    bat 'docker build -t carsale-backend .'
                }
            }
        }

        stage('Run Application'){
             steps{
                script{
                    bat 'docker stop mysqldb-container'
                    bat 'docker stop carsale-app-container'
                    bat 'docker start mysqldb-container'
                    bat 'docker start carsale-app-container'
                }
            }
        }
    }
}
