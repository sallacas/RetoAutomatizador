pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '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', url: 'https://github.com/sallacas/RetoAutomatizador']]])
            }
        }
       stage('Ejecucion Pruebas'){
			steps{
				script{
					try{
				   		//bat para windows
				   		echo 'inicio Test'
						bat ("gradlew clean test aggregate")
						echo 'Finaliza Test'
						currentBuild.result = 'SUCCESS'
				    }
				    catch(errortest){
				    	echo 'Error Test: ' + errortest
				    	currentBuild.result ='UNSTABLE'
				    }
				}
        	}
		}
    }
}