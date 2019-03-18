def releaseVersion = 'UNKNOWN'
def releaseTag = 'UNKNOWN'
pipeline { 
    agent any
    environment {
             GITHUB_DEVON4J_CREDENTIALS = credentials('jenkins-user-for-devon4j-github')           
    }
    parameters{
        string(
            description: 'Issue number for current release on Github',
            name:'release_issue',
            defaultValue: ''
        )
        string(
            description: 'Next planned release(without SNAPSHOT)',
            name:'next_planned_release',
            defaultValue: ''
        )
    }
    stages {
        stage('Prepare Code'){
            steps{
                sh "sed -i 's/-SNAPSHOT//g' pom.xml"
                script{
                    releaseVersion = readMavenPom().getVersion()
                    releaseTag = "release/" + releaseVersion
                }
                echo "releaseVersion: ${releaseVersion}"
                echo "releaseTag: ${releaseTag}"
                echo "${env.BRANCH_NAME}"
                
                sh "git add pom.xml"
                sh "git commit -m '${params.release_issue}: Bump the version to release version ${releaseVersion} by removing the -SNAPSHOT'"
                sh "git tag -a ${releaseTag} -m '#${params.release_issue}: tagged ${releaseVersion}'"
                sh "git tag"
                sh "git checkout ${releaseTag}"
            }
        }
        stage('Test & Package'){
            steps{
                sh "mvn clean package"
            }
        }
        stage('Sign Artifacts'){
            steps{
                withCredentials([file(credentialsId: '850ce103-c928-487a-9dd9-0d494194254c', variable: 'KEYRING')]) {
                    sh 'gpg --batch --import "${KEYRING}"'
                    sh 'for fpr in $(gpg --list-keys --with-colons  | awk -F: \'/fpr:/ {print $10}\' | sort -u); do echo -e "5\ny\n" |  gpg --batch --command-fd 0 --expert --edit-key ${fpr} trust; done'
                    configFileProvider([configFile(fileId: '44eaa7a2-d003-4348-b6b4-a61fd967e2ca', variable: 'MAVEN_SETTINGS')]) {
                        sh "mvn -gs $MAVEN_SETTINGS clean verify"
                  }
               }
            }
        }
        stage('Publish to Nexus'){
            steps{
                configFileProvider([configFile(fileId: '44eaa7a2-d003-4348-b6b4-a61fd967e2ca', variable: 'MAVEN_SETTINGS')]) {
                    sh "mvn -gs $MAVEN_SETTINGS -e -X deploy"
                }
            }
        }
        stage('Release on GitHub'){
            steps{
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'jenkins-user-for-devon4j-github', usernameVariable: 'GITHUB_DEVON4J_CREDENTIALS_USR', passwordVariable: 'GITHUB_DEVON4J_CREDENTIALS_PSW']]) { 
                    sh("git push http://$GITHUB_DEVON4J_CREDENTIALS_USR:$GITHUB_DEVON4J_CREDENTIALS_PSW@github.com/AbhayChandel/New-project.git HEAD:${env.BRANCH_NAME} ${releaseTag}")
                }
            }
        }
        stage('Set Next Planned Release'){
            steps{
                sh "sed -i 's/${releaseVersion}/${params.next_planned_release}-SNAPSHOT/g' pom.xml"
                sh "git add pom.xml"
                sh "git commit -m '${params.release_issue}: opened next snapshot version'"
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'jenkins-user-for-devon4j-github', usernameVariable: 'GITHUB_DEVON4J_CREDENTIALS_USR', passwordVariable: 'GITHUB_DEVON4J_CREDENTIALS_PSW']]) { 
                    sh("git push http://$GITHUB_DEVON4J_CREDENTIALS_USR:$GITHUB_DEVON4J_CREDENTIALS_PSW@github.com/AbhayChandel/New-project.git HEAD:${env.BRANCH_NAME}")
                }
            }
        }
    }
}
