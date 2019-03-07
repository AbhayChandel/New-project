pipeline { 
    agent any
    parameters{
        choice(
            description:'The type of release this is: A major, minor or a bug fix release.',
            name:'release-type',
            choices:'major\nminor\nbugfix'
        )
        string(
            description: 'Select branch from where to trigger the release.',
            name:'branch-name'
        )
    }
    stages {
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
    }
}
