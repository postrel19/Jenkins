pipeline {

    agent none

    parameters{
        booleanParam(name: 'ALFA', defaultValue: true, description: 'Build ALFA')
        booleanParam(name: 'BETA', defaultValue: true, description: 'Build BETA')
        booleanParam(name: 'ZETA', defaultValue: false, description: 'Build ZETA')

        booleanParam(name: 'All', defaultValue: true, description: 'Build All Platforms')
        booleanParam(name: 'Win64', defaultValue: false, description: 'Build Win64')
        booleanParam(name: 'PS4', defaultValue: false, description: 'Build PS4')
        booleanParam(name: 'PS5', defaultValue: false, description: 'Build PS5')
        booleanParam(name: 'XSX', defaultValue: false, description: 'Build XSX')
        booleanParam(name: 'XBoxOneGDK', defaultValue: false, description: 'Build XBoxOneGDK')
        booleanParam(name: 'Server', defaultValue: false, description: 'Build Server')
    }
    stages {
        stage('Run builds') {
            steps {
            PlatformsForBuild = ['PS4','XSX','PS5','XBoxOneGDK','Win64','Server']
            AllAgents = ['ALFA','BETA','ZETA']
            Platforms =[:]
            Agents = [:]
            for (p in PlatformsForBuild){
                if (params.p){
                    Platforms.p = true
                }
            }
            for (n in AllAgents){
                if (params.n){
                    Agents.n = true
                }
            }
            for (p in PlatformsForBuild){
                if (Platforms.p){
                    for (n in AllAgents){
                        if (Agents.n){
                            parallel {
                                Agents.n=false
                                    stage("${PlatformsForBuild.p} in ${AllAgents.n}") {
                                        agent {
                                            docker {image 'docker/getting-started '}
                                        }
                                        steps {
                                            echo p+"${PlatformsForBuild.p}"+n+"${AllAgents.n}"
                                            Platforms.p=false
                                            sh 'node --version'
                                        }
                                    }
                                Agents.n=true
                            }
                        }
                    break
                    }
                }
            }
            }
        }
    }
}