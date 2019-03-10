pipeline { 
    agent any
    stages {
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
        stage('prepare code'){
            steps{
            sh 'env'      
            sh """
               SHORTREV=`git rev-parse --short HEAD`
            """
            script {
            def pom = readMavenPom file: 'pom.xml'            
            // Now you have access to raw version string in pom.version
            // Based on your versioning scheme, automatically calculate the next one            
            VERSION = pom.version.replaceAll('SNAPSHOT', BUILD_TIMESTAMP + "." + SHORTREV)
            }
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
