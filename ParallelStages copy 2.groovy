pipeline {
    agent any
    options {
        parallelsAlwaysFailFast()
    }
    stages {
        stage('Non-Parallel Stage') {
            steps {
                echo 'This stage will be executed first.'
            }
        }
        stage('Parallel Stage') {
            parallel {
                if (params.ALFA){
                stage('Branch A') {
                    agent label 'ALFA'{
                            
                    }
                        steps {

                        }
                    }
                }
                if (params.BETA){
                stage('Branch B') {
                    agent label 'BETA'{

                        }
                    steps {

                    }
                }
                }

            }
        }
    }
}