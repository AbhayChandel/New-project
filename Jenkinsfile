pipeline { 
    agent any
    stages {
        stage('git'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']],
     userRemoteConfigs: [[url: 'https://github.com/AbhayChandel/New-project.git']]])
            }
        }
        stage('prepare code'){
            steps{     
            sh "sed -i 's/-SNAPSHOT//g' pom.xml"
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
        stage('build'){
            steps{
                sh "mvn clean package"
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
