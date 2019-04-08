#!groovy

node('master') {
  stage('Checkout') {
    checkout scm
  }

  stage('Compile') {
    withMaven(maven: 'Maven 3') {
      sh 'mvn clean compile'
    }
  }
}
    
