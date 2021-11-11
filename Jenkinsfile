pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh  "mvn test"
            }
        }
    }

    post {
        always {
            script {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
            ])
            }
        }
    }
}