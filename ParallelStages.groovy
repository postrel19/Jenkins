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
        stage('Parallel Stage') {
            parallel {
                stage ('init'){
                    steps {
                        script{
                                  
                        }
                    }
                }
                stage('Branch A') {
                    agent any
                    steps {
                        script{
                            Massive = ['a':'true','b':'true','c':'true','d':'true','e':'true','f':'true']
                            String [] MassInMass = ['a','b','c','d','e','f']
                            funk1(Massive,MassInMass)
                        }
                    }
                }
                stage('Branch B') {
                    agent any
                    steps {
                        script{
                            Massive = ['a':'true','b':'true','c':'true','d':'true','e':'true','f':'true']
                            MassInMass = ['a','b','c','d','e','f']
                            funk1(Massive, MassInMass)
                        }
                    }
                        
                }
            }
        }
    }
}