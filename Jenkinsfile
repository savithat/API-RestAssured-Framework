pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }
        
    environment{
   
        BUILD_NUMBER = "${BUILD_NUMBER}"
   
    }
    

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
                
        stage('Regression API automation Tests') {
    steps {
        catchError(buildResult: 'SUCCESS' StageResult:'FAILURE'){
        git 'https://github.com/savthi/API-RestAssured-Framework'
        sh 'maven clean install'
        }
    		}
		}
		
		
		
		 stage('Run Docker Image with Regression Tests') {
    steps {
        script {
        		allure([
        		includeProperties: false,
        		jdk: '',
        		properties:[],
        		reportBuildPolicy:'ALWAYS',
        		results: path:[['/allure-results']]
        		])
        	
       			 }
    		}
		}
		
		
		
		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'reports', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
         

         
    }
}