def MassInMass = ['a','b','c','d','e','f']
def Massive = [a:'true',b:'true',c:'true',d:'true',e:'true',f:'true']
def foo1 = {m, mm ->
    for (p in mm){
        if (Massive.p){
            echo p
            Massive.p = false
            echo p
            sleep(10)
        }
    }
}
def foo2 = {m,mm ->
    for (p in mm){
        if (MassInMass.p){
            echo p
            Massive.p = false
            echo p
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
                echo MassInMas
                echo Massive
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
                        foo1(Massive, MassInMass)
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
                        foo2 (Massive, MassInMass)
                        }
                    }
                        
                }
            }
        }
    }
}