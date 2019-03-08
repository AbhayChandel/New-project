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
                echo "Building configuration Release type: ${params.ReleaseType}"  
                echo "Building configuration Branch name: ${params.BranchName}"
                echo "Building configuration Branch name: ${params.VersionToRelease}"
            }
        }
        stage('clean') { 
            steps { 
                sh "mvn clean"
            }
        }
        stage('Merge To Feature Branch') { 
            when { equals expected: bugfix, actual: ${ReleaseType} }
                   steps {
                           echo 'Merging Bug fixed to develop branch.'
                   }
           }
        }
}
