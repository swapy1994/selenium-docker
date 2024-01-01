
// this file is especially for windows bat approach
pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                //for windows users use 'bat' :-
                bat "mvn clean package -DskipTests"
                //for linux and mac users use 'sh' : sh "docker run -e NUMBER=${NUMBER} swapy1994/squares"
            }
        }

        stage('Build image'){
            steps{
                bat "docker build -t=swapy1994/selenium-tests ."
            }
        }

        stage('Push Image'){
        	environment{
        	    // 'dockerhub-creds' is the ID of docker credentials that we have saved in jenkins manage credentials option.
                DOCKER_HUB = credentials('dockerhub-creds')    
        	}

            steps{
            	// for working linux machines use ${} instead of %%
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push swapy1994/selenium-tests"
            }
        }

    }
    
    post{
        always {
            
            bat "docker logout"
        }

        
    }


}