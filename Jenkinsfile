pipeline { 
    agent any
    stages {
        stage('git'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
     userRemoteConfigs: [[url: 'https://github.com/AbhayChandel/New-project.git']]])
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
        stage('prepare code'){
            steps{     
            sh "sed -i 's/-SNAPSHOT//g' pom.xml"
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
