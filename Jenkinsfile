pipeline { 
    agent any
    environment {
                GITHUB_DEVON4J_CREDENTIALS = credentials('jenkins-user-for-devon4j-github')
    }
    parameters{
        string(
            description: 'Issue number for release on devon4j Github',
            name:'ReleaseIssue',
            defaultValue: ''
        )
    }
    stages {
        stage('clone'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']],
     userRemoteConfigs: [[url: 'https://github.com/AbhayChandel/New-project.git']]])
                sh "git config user.name 'John Doe'"
                sh "git config user.email 'foo@bar.bla'"
            }
        }
        stage('prepare code'){
            steps{
                
                sh "sed -i 's/-SNAPSHOT//g' pom.xml"
                //withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'jenkins-user-for-devon4j-github', usernameVariable: 'GITHUB_DEVON4J_CREDENTIALS_USR', passwordVariable: 'GITHUB_DEVON4J_CREDENTIALS_PSW']]) {
                       //sh("git tag -a some_tag -m 'Jenkins'")
                       //sh('git push git://${GIT_USERNAME}:${GIT_PASSWORD}@bitbucket.org:myproj.git')
                    sh "git add pom.xml"
                    sh "git commit -m '${params.ReleaseIssue}: Bump the version to release version by removing the -SNAPSHOT'"
                    sh "git tag -a release/1.0.0 -m '#${params.ReleaseIssue}: tagged 1.0.0'"
                    sh "git tag"
                    sh "git checkout release/1.0.0"
               // }
            }
        }
        stage('clean & build'){
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
