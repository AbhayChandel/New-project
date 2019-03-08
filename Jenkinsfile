pipeline { 
    agent any
    parameters{
        choice(
            description:'The type of release this is: A major, minor or a bug fix release.',
            name:'ReleaseType',
            choices:'major\nminor\nbugfix'
        )
        string(
            description: 'Select branch from where to trigger the release.',
            name:'BranchName',
            defaultValue: 'develop'
        )
        string(
            description: 'Version for this release.',
            name:'VersionToRelease',
            defaultValue: ''
        )
    }
    stages {
        stage('validate'){
            steps{
                echo "Building configuration Release type: ${params.ReleaseType}"
                script {
                        if (${params.ReleaseType} == 'bugfix') {
                              echo 'Will merge this branch onto develop'
                        } else {
                              echo 'No merging required.'
                        }
                }  
                echo "Building configuration Branch name: ${params.BranchName}"
                echo "Building configuration Branch name: ${params.VersionToRelease}"
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
    }
}
