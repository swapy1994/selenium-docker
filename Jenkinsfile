
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
                script {
                    app = docker.build('swapy1994/selenium-tests')
                }
            }
        }

        stage('Push Image'){

            steps{
            	script {
                    // registry url is blank for dockerhub offically so we will leave it blank.
                    docker.withRegistry('', 'dockerhub-creds'){
                        app.push("latest")
                    }
                }
            }
        }

    }
    
    post{
        always {
            
            bat "docker logout"
        }

        
    }


}