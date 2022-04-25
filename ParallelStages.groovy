

def funk1 = {massiv, names ->
    echo massiv
    echo names
    for (param in names){
        echo param
        echo massiv.param
        if (massiv.param){
            massiv.param=false
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
                                def Massive = ['a':'true','b':'true','c':'true','d':'true','e':'true','f':'true']
                    def MassInMass = ['a','b','c','d','e','f'] as String[]
            parallel {
                stage('Branch A') {
                    agent any
                    steps {
                        script{
                            funk1(env.Massive, env.MassInMass)
                        }
                    }
                }
                stage('Branch B') {
                    agent any
                    steps {
                        script{
                            funk1(env.Massive, env.MassInMass)
                        }
                    }
                        
                }
            }
        }
    }
}