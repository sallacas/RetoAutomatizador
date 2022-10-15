#!groovy
import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def CORREOS = ""
def urlRepo=""
def rama="master"
def credenciales=""

pipeline {
    agent any
    stages {
        stage('Preparar Workspace'){
            steps{
                script{
                    env.WORKSPACE_LOCAL = bat(returnStdout: true, script: 'cd').trim()
                    env.BUILD_TIME = bat(returnStdout: true, script: 'date +/F-/T').trim()
                    echo "Workspace set to:" + env.WORKSPACE_LOCAL
                    echo "Build time:" + env.BUILD_TIME
                }
            }
        }
        stage('Obtener Fuentes'){
            steps {
                    checkout([$class: 'GitSCM', branches: [[name: rama]],
                    wdoGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [
                    [credentialsId: credenciales, url: urlRepo]]])
            }
        }

        stage('SonarQube analysis'){
            steps {
               script {
                        scannerHome = tool 'SonarQubeScanner'//mismo nombre del servidor configurado en las Global Tools Jenkins
               }
               withSonarQubeEnv('sonarQube')//mismo nombre del servidor configurado en la configuracion del sistema jenkins
               {
                  bat 'sonar-scanner'
               }
            }
        }

        stage("Quality Gate") {
            steps {
                script {
                    try {
                        timeout(time: 1, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
                            currentBuild.result = 'SUCCESS'
                        }
                    }
                    catch (ex) {
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage('Ejecutar Pruebas') {
            steps {
                script {
                    try {
                        //bat ("gradle clean test -DRunner=\"${Runner}\" aggregate") //Ejecución en agente Windows con parametro jenkins
                        //sh ("gradle clean test -DRunner=\"${Runner}\" aggregate") //Ejecución en agente Linux con parametro jenkins
                        bat("gradle clean test aggregate") //Ejecución en agente windows sin parametro jenkins
                        echo 'Test Ejecutados sin Fallo'
                        currentBuild.result = 'SUCCESS'
                    }
                    catch (ex) {
                        echo 'Test Ejecutados con Fallo'
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Generar evidencia'){
            steps{
                 script{
                     try{
                          bat  " rename \"${WORKSPACE}\\target\" serenity_${timestamp}"
                          echo 'Backup de evidencias realizado con exito'
                          publishHTML([
                                allowMissing: false,
                                alwaysLinkToLastBuild: true,
                                keepAll: true,
                                reportDir: "${WORKSPACE}//serenity_${timestamp}",
                                reportFiles: 'index.html',
                                reportName: 'Evidencias Automatizacion WEB Screenplay',
                                reportTitles: 'Proyecto Mobiletec Screenplay'
                          ])
                                echo 'Reporte Serenity realizado con exito'
                                archiveArtifacts "**/cucumber.json"
                                cucumber '**/cucumber.json'
                                echo 'Reporte Cucumber realizado con exito'
                          }
                          catch(e){
                                echo 'No se realizo el Backup de evidencias'
                                publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "${WORKSPACE}//target/serenity_${timestamp}", reportFiles: 'index.html', reportName: 'Evidencias Automatizacion WEB Screenplay', reportTitles: 'Proyecto Mobiletec Screenplay'])
                                echo 'Reporte Html realizado con exito'
                                currentBuild.result='SUCCESS'
                          }
                 }
            }
        }

        stage('Importar resultados en XRAY'){
            steps{
                script{
                    def titulo="${TITULO_ISSUE}"
                    def description = "${DESCRIPTION_ISSUE}"
                    def buildNumber="[BUILD_NUMBER|${BUILD_NUMBER}]"
                    def buildUser= env.BUILD_USER_ID
                    def labels = '["@Login","@PurchaseABook"]'
                    def environment = "QA"
                    def testExecutionFieldId = 10011
                    def typeEnvironmentField="customfield_10040"
                    def projectKey = "XRAYJ"
                    def xrayConnectorId = 'cd8de758-d627-4be8-a825-6710ffab489e'
                    def info =
                    '''{
                        "fields":
                        {
                            "project":
                            {
                               "key": "''' + projectKey + '''"
                            },                          
                            "issuetype":
                            {
                                "id": "''' + testExecutionFieldId + '''"
                            },
                            "summary": "'''+titulo+''+buildNumber+''+buildUser+'''",  
                            "description": "'''+description+'''",
                            "labels": '''+labels+''', 
                            "''' + typeEnvironmentField + '''":
                            {
                                "value": "''' + environment + '''"
                            } 
                        }
                    }'''
                    echo info
                    step([$class: 'XrayImportBuilder', endpointName: '/cucumber/multipart', importFilePath: "serenity_${timestamp}/cucumber-reports/cucumber.json", importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
                }
            }
        }
        stage('Creando Bug XRAY JIRA'){
            steps{
                script{
                    def titulo="Error"
                    def description = "Error en el escenario "
                    def buildNumber="[BUILD_NUMBER|${BUILD_NUMBER}]"
                    def buildUser= env.BUILD_USER_ID
                    def labels = '["@Error","@Bug"]'
                    def environment = "QA"
                    def bugFieldId = 10004
                    def typeEnvironmentField="customfield_10040"
                    def projectKey = "XRAYJ"
                    def xrayConnectorId = 'cd8de758-d627-4be8-a825-6710ffab489e'
                    def info =
                            '''{
                        "fields":
                        {
                            "project":
                            {
                               "key": "''' + projectKey + '''"
                            },                          
                            "issuetype":
                            {
                                "id": "''' + bugFieldId + '''"
                            },
                            "summary": "'''+titulo+''+buildNumber+''+buildUser+'''",  
                            "description": "'''+description+'''",
                            "labels": '''+labels+''', 
                            "''' + typeEnvironmentField + '''":
                            {
                                "value": "''' + environment + '''"
                            } 
                        }
                    }'''
                    echo info
                    step([$class: 'XrayImportBuilder', endpointName: '/cucumber/multipart', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
                }
            }
        }
        stage('Notificar') {
            steps {
                script {
                    if (currentBuild.result == 'UNSTABLE')
                        currentBuild.result = 'FAILURE'

                    if (currentBuild.result == 'SUCCESS')
                        emailext(
                                subject: "FRM SCREENPLAY - EJECUCION EXITOSA ESCENARIOS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                                body: """<p><b style="color:MediumSeaGreen;">EJECUCION EXITOSA:</b> Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            				<p><b>Para verificar el estado de la ejecucion ingrese a:</b> &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                                to: "${CORREOS}"
                        )
                    if (currentBuild.result == 'FAILURE')
                        emailext(
                                subject: "FRM SCREENPLAY - EJECUCION FALLIDA ESCENARIOS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                                body: """<p><b style="color:red;">EJECUCION FALLIDA:</b> Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            				<p><b>Para verificar el estado de la ejecucion ingrese a:</b> &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                                to: "${CORREOS}"
                        )
                }
            }
        }
    }

        post {
                always {
                    publishTestResults serverAddress: 'https://jessidcordoba.atlassian.net/',
                    projectKey: 'TES',
                    format: 'Cucumber',
                    filePath: 'target/cucumber-reports/*.json',
                    autoCreateTestCases: false,
                      customTestCycle: [
                        name: 'Jenkins Build',
                        description: 'Results from Jenkins Build',
                        jiraProjectVersion: '10001',
                        folderId: '3040527',
                        customFields: '{"number":50,"single-choice":"option1","checkbox":true,"userpicker":"5f8b5cf2ddfdcb0b8d1028bb","single-line":"a text line","datepicker":"2020-01-25","decimal":10.55,"multi-choice":["choice1","choice3"],"multi-line":"first line<br />second line"}'
                      ]
                }
        }
}