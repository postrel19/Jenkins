env.MassInMass = ['a','b','c','d','e','f']
env.Massive = ['a':'true','b':'true','c':'true','d':'true','e':'true','f':'true']
def foo1 = {m ->
echo env.Massive
echo env.MassInMass
    for (par in env.MassInMas){
        echo par+' hui'
        if (env.Massive.par){
            echo par
            env.Massive.par = false
            echo par
            sleep(10)
        }
    }
}
def foo2 = {m ->
echo env.Massive
echo env.MassInMass
    for (par in env.MassInMas){
        echo par+' hui'
        if (env.MassInMass.par){
            echo par
            env.Massive.par = false
            echo par
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
                echo env.MassInMass
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