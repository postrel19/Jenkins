pipeline {
    agent {
        label 'docker'
    }
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
                stage('Branch A') {
                    agent {
                         docker {
                            image 'docker/getting-started'
                            args "-v \${PWD}:/Users/postrel19/Desktop/GitHab/ -w /usr/src/app"
                            reuseNode true
                            label "build-image"
                         }
                    }
                    steps {
                        echo "On Branch A"
                    }
                }
                stage('Branch B') {
                    agent {
                        docker {
                            image 'docker/getting-started'
                            args "-v \${PWD}:/Users/postrel19/Desktop/GitHab/ -w /usr/src/app"
                            reuseNode true
                            label "build-image"
                            }
                    }
                    steps {
                        echo "On Branch B"
                    }
                }
                stage('Branch C') {
                    agent {
                        docker {
                            image 'docker/getting-started'
                            args "-v \${PWD}:/Users/postrel19/Desktop/GitHab/ -w /usr/src/app"
                            reuseNode true
                            label "build-image"
                            }
                    }
                    stages {
                        stage('Nested 1') {
                            steps {
                                echo "In stage Nested 1 within Branch C"
                            }
                        }
                        stage('Nested 2') {
                            steps {
                                echo "In stage Nested 2 within Branch C"
                            }
                        }
                    }
                }
            }
        }
    }
}