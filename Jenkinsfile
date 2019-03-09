pipeline { 
    agent any
    parameters{
        string(
            description: 'Version for this release.',
            name:'ReleaseVersion',
            defaultValue: ''
        )
    }
    stages {
        stage('validate'){
            steps{
                echo "Building configuration Branch name: ${params.ReleaseVersion}"
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
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
