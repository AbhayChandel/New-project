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
            name:'BranchName'
        )
    }
    stages {
        stage('validate'){
            steps{
                echo "Building configuration: ${params.ReleaseType}"
                echo "Building configuration: ${params.BranchName}"
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
    }
}
