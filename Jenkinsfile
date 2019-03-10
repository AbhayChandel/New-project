pipeline { 
    agent any
    environment {
    //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
    VERSION = readMavenPom().getVersion()
    }
    stages {
        stage('clean') { 
            steps { 
                sh 'printenv'
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
