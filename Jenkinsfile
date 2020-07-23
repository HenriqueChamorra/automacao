properties([parameters([string(defaultValue: '1', description: 'Numero do build para atualizar os parametros.', name: 'buildRefresh'), string(defaultValue: 'dev', description: 'Ambiente que os testes serão rodados', name: 'env')]), pipelineTriggers([])])

node {
    if (params.buildRefresh == BUILD_NUMBER) {
        stage('Refreshing parameters') { }
    } else {
        def envType, gitUrl

        try {
            stage('Evaluating env') {
                envType = params.env;                                
                gitUrl = "https://github.com/HenriqueChamorra/automacao.git"
            }
    
            stage('Checking') {
                echo 'Checking Branch Build: ' +env.BRANCH_NAME
                checkout scm
            }
            stage('Testing') {
                sh "chmod +x -R ${WORKSPACE}"

                try {
                    sh "cd ${WORKSPACE}/ && ./gradle clean test"
                } finally {
                }
            }
     } catch (err) {
            currentBuild.result = "FAILURE"
            echo 'Error'
            throw err
        }
    }
}
