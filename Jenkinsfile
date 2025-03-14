pipeline {
    agent {
        docker {
            image 'maven:3.8.4-openjdk-17-slim'  
//            label 'maven'  // Label opcional, dependiendo de la configuración de tu entorno de Jenkins
//            args '-v $HOME/.m2:/root/.m2'  // Persistir el caché de Maven entre builds
        }
    }
    // environment {
    //     MVN_HOME = '/usr/share/maven'
    // }
    stages {
        stage('Compilar con Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Ejecutar Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archivar Artefactos') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            }
        }

        stage('Copiar Artefactos a otro Job') {
            steps {
                script {
                    copyArtifacts(projectName: 'NombreDelOtroJob', filter: '**/*.jar', target: 'artifacts/')
                }
            }
        }
    }

    post {
        success {
            echo 'La compilación y las pruebas fueron exitosas.'
        }
        failure {
            echo 'Hubo un error en la compilación o las pruebas.'
        }
    }
}
