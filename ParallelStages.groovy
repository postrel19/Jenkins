env.Massive = [a:'true',b:'true',c:'true',d:'true',e:'true',f:'true']
def foo1 = {m ->
    for (n in m){
        if (n){
            echo n
            nv.Massive.n = false
            echo n
            sleep(10)
        }
    }
}
def foo2 = {m ->
    for (n in m){
        if (n){
            echo n
            env.Massive.n = false
            echo n
            sleep(10)

        }
    }
}
pipeline {
    agent any
    options {
        parallelsAlwaysFailFast()
    }
    stages {
        stage('Non-Parallel Stage') {
            steps {
               echo env.Massive
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
                        script{
                        foo1(env.Massive)
                        }
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
                        script{
                        foo2 (env.Massive)
                        }
                    }
                        
                }
            }
        }
    }
}