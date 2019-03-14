def releaseVersion = 'UNKNOWN'
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
        stage('Checkout SCM'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']],
     userRemoteConfigs: [[url: 'https://github.com/AbhayChandel/New-project.git']]])
                sh "git config user.name 'AbhayChandel'"
                sh "git config user.email 'abhay.chandel@capgemini.com'"
            }
        }
        /*stage('prepare code'){
            steps{
                sh "sed -i 's/-SNAPSHOT//g' pom.xml"
                script{
                    releaseVersion = readMavenPom().getVersion()
                }
                echo "releaseVersion: ${releaseVersion}"
                
                sh "git add pom.xml"
                sh "git commit -m '${params.ReleaseIssue}: Bump the version to release version ${releaseVersion} by removing the -SNAPSHOT'"
                sh "git tag -a release/${releaseVersion} -m '#${params.ReleaseIssue}: tagged ${releaseVersion}'"
                sh "git tag"
                sh "git checkout release/${releaseVersion}"
            }
        }*/
        stage('clean & test'){
            steps{
                sh "mvn clean test"
            }
        }
        /*stage('package & publish'){
            steps{
                sh "mvn package"
            }
        }*/
        stage('sign artifacts'){
            steps{
                withCredentials([file(credentialsId: '488de859-db5e-49f3-abe2-04bc5ae2c2db', variable: 'KEYRING')]) {
                //sh 'gpg --batch --import "${KEYRING}"'
                sh 'export GPG_TTY=$(tty)'
                sh 'gpg --import "${KEYRING}"'
                sh 'mvn clean verify'
               }
            }
        }
        /*stage('publish to nexus'){
            steps{
                configFileProvider([configFile(fileId: '44eaa7a2-d003-4348-b6b4-a61fd967e2ca', variable: 'MAVEN_SETTINGS')]) {
                    sh "mvn -gs $MAVEN_SETTINGS -e -X deploy"
                }
            }
        }*/
        /*stage('Merge To Feature Branch') { 
            //when { equals expected: bugfix, actual: "${ReleaseType}" }
            //when{expression { params.ReleaseType == 'bugfix' }}
            steps {
                // add configuration to set the version to next snapshot; decide on how to identify between
                // feature and bugfix branch. And whether to set next snapshot as major or minor release.
                echo 'Merging Bug fixed to develop branch.'
                
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'jenkins-user-for-devon4j-github', usernameVariable: 'GITHUB_DEVON4J_CREDENTIALS_USR', passwordVariable: 'GITHUB_DEVON4J_CREDENTIALS_PSW']]) { 
                sh("git push http://$GITHUB_DEVON4J_CREDENTIALS_USR:$GITHUB_DEVON4J_CREDENTIALS_PSW@github.com/AbhayChandel/New-project.git HEAD:develop")
                }
            }
        }*/
    }
}
