pipeline { 
    agent any
    environment {
                GITHUB_DEVON4J_CREDS = credentials('jenkins-user-for-devon4j-github')
    }
    parameters{
        string(
            description: 'Issue number for release on devon4j Github',
            name:'ReleaseIssue',
            defaultValue: ''
        )
    }
    stages {
        stage('git'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']],
     userRemoteConfigs: [[url: 'https://github.com/AbhayChandel/New-project.git']]])
            }
        }
        stage('prepare code'){
            steps{
                echo $GITHUB_DEVON4J_CREDS
                sh "sed -i 's/-SNAPSHOT//g' pom.xml"
                sh "git add pom.xml"
                sh "git commit -m '${params.ReleaseIssue}: Bump the version to release version by removing the -SNAPSHOT'"
                sh "git tag -a release/1.0.0 -m '#${params.ReleaseIssue}: tagged 1.0.0'"
                sh "git tag"
                sh "git checkout release/2.5.0"
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
