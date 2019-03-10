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
            sh """
               SHORTREV=`git rev-parse --short HEAD`
            """
            script {
            def pom = readMavenPom file: 'pom.xml'            
            // Now you have access to raw version string in pom.version
            // Based on your versioning scheme, automatically calculate the next one            
            OLD-VERSION = pom.version
            NEW-VERSION = pom.version.replaceAll('-SNAPSHOT',"")
            }
            sh "sed -i 's/OLD-VERSION/NEW-VERSION/g' pom.xml"
        }
        }
        stage('build'){
            steps{
                sh """
          mvn -B org.codehaus.mojo:versions-maven-plugin:2.5:set -DprocessAllModules package
      """
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
