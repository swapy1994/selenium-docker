
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
            steps{
                bat "docker push swapy1994/selenium-tests:v1.0"
            }
        }

    }

}