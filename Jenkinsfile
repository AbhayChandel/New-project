def releaseVersion = 'UNKNOWN'
pipeline { 
    agent any
    environment {
             GITHUB_DEVON4J_CREDENTIALS = credentials('jenkins-user-for-devon4j-github')
             VERSION = readMavenPom().getVersion()
             
    }
    parameters{
        string(
            description: 'Issue number for release on devon4j Github',
            name:'ReleaseIssue',
            defaultValue: ''
        )
    }
    stages {
        stage('Checkout SCM'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']],
     userRemoteConfigs: [[url: 'https://github.com/AbhayChandel/New-project.git']]])
                sh "git config user.name 'AbhayChandel'"
                sh "git config user.email 'abhay.chandel@capgemini.com'"
            }
        }
        stage('prepare code'){
            steps{
                sh "sed -i 's/-SNAPSHOT//g' pom.xml"
                script{
                    releaseVersion = readMavenPom().getVersion()
                }
                echo "releaseVersion: ${releaseVersion}"
                //version = readMavenPom().getVersion()
                //echo "${version}"
                    //sh "git add pom.xml"
                    //sh "git commit -m '${params.ReleaseIssue}: Bump the version to release version by removing the -SNAPSHOT'"
                    //sh "git tag -a release/1.0.0 -m '#${params.ReleaseIssue}: tagged 1.0.0'"
                    //sh "git tag"
                    //sh "git checkout release/1.0.0"
            }
        }
        stage('clean & deploy'){
            steps{
                sh "mvn clean package"
            }
        }
        
        stage('Merge To Feature Branch') { 
            //when { equals expected: bugfix, actual: "${ReleaseType}" }
            //when{expression { params.ReleaseType == 'bugfix' }}
            steps {
                echo 'Merging Bug fixed to develop branch.'
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'jenkins-user-for-devon4j-github', usernameVariable: 'GITHUB_DEVON4J_CREDENTIALS_USR', passwordVariable: 'GITHUB_DEVON4J_CREDENTIALS_PSW']]) { 
                //sh("git push http://$GITHUB_DEVON4J_CREDENTIALS_USR:$GITHUB_DEVON4J_CREDENTIALS_PSW@github.com/AbhayChandel/New-project.git HEAD:develop")
                }
            }
        }
    }
}
