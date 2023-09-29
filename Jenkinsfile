pipeline {
    agent any
    environment {
        PATH = "C:/Program Files/apache-maven-3.9.4/bin:$PATH"
    }
    stages {
        stage('Entrar no diretorio') {
            steps {
                dir('sistemaGerenciamento') {
                    script {
                        bat "mvn -Dmaven.test.failure.ignore=true clean test package"
                    }
                }
            }
        }
    }
}
