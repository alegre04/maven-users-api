pipeline {
    agent { label 'controller' }

    environment {
        IMAGE_NAME = "mi-aplicacion-java"
        IMAGE_TAG = "latest"
        DOCKERFILE_PATH = "Dockerfile"
        SONAR_PROJECT_KEY = "labmaven01"
        SONAR_PROJECT_NAME = "labmaven01"
        SONAR_SOURCES = "src/main"
        SONAR_TESTS = "src/test"
        SONAR_JUNIT_REPORTS_PATH = "target/surefire-reports"
        SONAR_JACOCO_REPORT_PATH = "target/jacoco.exec"
        SONAR_BINARIES = "target/classes"
        SONAR_SCANNER_HOME = tool name: 'sonar-scanner', type: 'ToolLocator'
    }

    stages {
        stage('Compilar con Maven') {
            agent {
                docker { image 'maven:3.8.4-openjdk-17-slim' }
            }
            steps {
                sh 'mvn clean package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, onlyIfSuccessful: true
            }
        }

        stage('Sonarqube') {
            agent {
                docker { image 'maven:3.8.4-openjdk-17-slim' }
            }
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh """
                        ${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                            -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                            -Dsonar.sources=${SONAR_SOURCES} \
                            -Dsonar.sourceEncoding=UTF-8 \
                            -Dsonar.tests=${SONAR_TESTS} \
                            -Dsonar.junit.reportsPath=${SONAR_JUNIT_REPORTS_PATH} \
                            -Dsonar.surefire.reportsPath=${SONAR_JUNIT_REPORTS_PATH} \
                            -Dsonar.jacoco.reportPath=${SONAR_JACOCO_REPORT_PATH} \
                            -Dsonar.java.binaries=${SONAR_BINARIES} \
                            -Dsonar.java.coveragePlugin=jacoco \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco.xml \
                            -Dsonar.exclusions=**/*IT.java,**/*TEST.java,**/*Test.java,**/src/it**,**/src/test**,**/gradle/wrapper** \
                            -Dsonar.java.libraries=target/*.jar
                    """
                }
            }
        }

        stage('Ejecutar Tests') {
            agent {
                docker { image 'maven:3.8.4-openjdk-17-slim' }
            }
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Image') {
            steps {
                copyArtifacts filter: 'target/*.jar',
                              fingerprintArtifacts: true,
                              projectName: '${JOB_NAME}',
                              flatten: true,
                              selector: specific('${BUILD_NUMBER}'),
                              target: 'target'
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -f ${DOCKERFILE_PATH} ."
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    sh "docker run -d -p 8090:8080 ${IMAGE_NAME}:${IMAGE_TAG}"
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
