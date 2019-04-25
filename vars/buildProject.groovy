def call(body) {
  // evaluate the body block, and collect configuration into the object
  def pipelineParams= [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = pipelineParams
  body()
  
  pipeline {
    agent any

    stages {
      stage ('Clone') {
        steps {
          checkout scm
         }
      }

      stage('Compile') {
        steps {
          withMaven(maven: 'Maven 3.6.0') {
            sh 'mvn clean compile'
            echo "Project name" + pipelineParams.projectName
          }
        }
      }
    }
  }
}
