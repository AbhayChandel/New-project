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
    }
    stages {
        stage('validate'){
            steps{
                echo "Building configuration Release type: ${params.ReleaseType}"
                echo "Building configuration Branch name: ${params.BranchName}"
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
    }
}
