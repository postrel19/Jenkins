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
                stage('Branch A') {
                    agent any
                    // { label 'master'
                    //     //  docker {
                    //     //     image 'docker/getting-started'
                    //     //     args "-v \${PWD}:/Users/postrel19/Desktop/GitHab/ -w /usr/src/app"
                    //     //     reuseNode true
                    //     //     label "build-image"
                    //     //}
                    // }
                    steps {
                        sleep(random(10,50))
                        echo '1'
                        echo "On Branch A"
                    }
                }
                stage('Branch B') {
                    agent any
                    // { label 'master'
                    //     // docker {
                    //     //     image 'docker/getting-started'
                    //     //     args "-v \${PWD}:/Users/postrel19/Desktop/GitHab/ -w /usr/src/app"
                    //     //     reuseNode true
                    //     //     label "build-image"
                    //         //}
                    // }
                    steps {
                        sleep(random(10,50))
                        echo '1'
                        echo "On Branch B"
                    }
                }
                stage('Branch C') {
                    agent any
                    // { label 'master'
                    //         // on interrupt do
                        
                    //     // docker {
                    //     //     image 'docker/getting-started'
                    //     //     args "-v \${PWD}:/Users/postrel19/Desktop/GitHab/ -w /usr/src/app"
                    //     //     reuseNode true
                    //     //     label "build-image"
                    //     //     }
                    // }
                    stages {
                        stage('Nested 1') {
                            steps {
                        sleep(random (10,50))
                        echo '1'
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