pipeline {
  agent any
  triggers {
    cron('H/50 * * * *')
  }
  stages {
    stage("Configuration"){
        steps {
            bat(script: 'rd /q /s allure-report-final 2>nul & git clone https://github.com/MonolithOfficial/allure-report-final.git', encoding: 'UTF-8', label: 'CLONE project')
        }
    }
    stage('RUN') {
      parallel {
        stage('SoapUI') {
          steps {
            bat(script: 'C:\\"Program Files"\\SmartBear\\SoapUI-5.6.0\\bin\\testrunner.bat soapui\\SoapUiPart.xml', encoding: 'UTF-8', label: 'EXECUTE SoapUI')
          }
        }
        stage('Maven') {
          steps {
            bat(script: 'jenkins/MavenLauncher.bat', encoding: 'UTF-8', label: 'EXECUTE Maven')
          }
        }
        stage('JMeter') {
          steps {
            bat(script: 'jenkins/JmeterLauncher.bat', encoding: 'UTF-8', label: 'EXECUTE JMeter')
          }
        }
      }
    }
    stage("Publish"){
        steps {
            bat(script: 'cd allure-report-final & git add . & git commit -m "update report" & git push -u origin master & cd ..', encoding: 'UTF-8', label: 'PUBLISH reports')
        }
    }

  }
    post {
    always {
      echo 'BUILD FINISHED.'
    }
    success {
      emailext attachLog: true, attachmentsPattern: 'soapui/TestReports/Report.txt',
      mimeType: 'text/html',
      body: "<a href='https://allurereportfinal.netlify.app'><h1>Maven Allure report</h1></a><br><a href='https://jmeterreportfinal.netlify.app/'><h1>JMeter report</h1></a><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER}",
      replyTo: 'cotneaburjania@gmail.com',
      subject: "SUCCESS CI: Project name -> ${env.JOB_NAME}: Jenkins Build ${currentBuild.currentResult}",
      to: 'cotneaburjania@gmail.com'
    }
    failure {
      emailext attachLog: true, attachmentsPattern: 'soapui/TestReports/Report.txt',
      mimeType: 'text/html',
      body: "<a href='https://allurereportfinal.netlify.app'><h1>Maven Allure report</h1></a><br><a href='https://jmeterreportfinal.netlify.app/'><h1>JMeter report</h1></a><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER}",
      replyTo: 'cotneaburjania@gmail.com',
      subject: "ERROR CI: Project name -> ${env.JOB_NAME}: Jenkins Build ${currentBuild.currentResult}",
      to: 'cotneaburjania@gmail.com'
    }
    unstable {
      emailext attachLog: true, attachmentsPattern: 'soapui/TestReports/Report.txt',
      mimeType: 'text/html',
      body: "<a href='https://allurereportfinal.netlify.app'><h1>Maven Allure report</h1></a><br><a href='https://jmeterreportfinal.netlify.app/'><h1>JMeter report</h1></a><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER}",
      replyTo: 'cotneaburjania@gmail.com',
      subject: "UNSTABLE CI: Project name -> ${env.JOB_NAME}: Jenkins Build ${currentBuild.currentResult}",
      to: 'cotneaburjania@gmail.com'
    }

    changed {
      emailext attachLog: true, attachmentsPattern: 'soapui/TestReports/Report.txt',
      mimeType: 'text/html',
      body: "<a href='https://allurereportfinal.netlify.app'><h1>Maven Allure report</h1></a><br><a href='https://jmeterreportfinal.netlify.app/'><h1>JMeter report</h1></a><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER}",
      replyTo: 'cotneaburjania@gmail.com',
      subject: "STATUS CHANGED CI: Project name -> ${env.JOB_NAME}: Jenkins Build ${currentBuild.currentResult}",
      to: 'cotneaburjania@gmail.com'
    }
  }
}