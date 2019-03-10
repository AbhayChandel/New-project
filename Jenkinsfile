pipeline { 
    agent any
    environment {
    //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
    pom = readMavenPom file: 'pom.xml'
    VERSION = pom.version
    }
    stages {
        stage('clean') { 
            steps { 
                sh 'echo ${env.BRANCH_NAME}'
                sh 'echo ${env.VERSION}'
                sh "mvn clean"
            }
        }
        stage('Merge To Feature Branch') { 
            //when { equals expected: bugfix, actual: "${ReleaseType}" }
            //when{expression { params.ReleaseType == 'bugfix' }}
            steps {
                echo 'Merging Bug fixed to develop branch.'
            }
        }
    }
}
