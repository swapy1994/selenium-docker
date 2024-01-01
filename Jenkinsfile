
// this approach considers that node machine has not installed with maven. so we will install maven as well
// also this approach will work only on linux and mac os.
pipeline{

    agent none

    stages{

        stage('Build Jar'){
            agent {
                // when jenkins creates the container it will do the volume mapping. so our code will be available inside the
                //container. in our case it is mapped with under mapped-volumes of 01-jenkins folder in SELENIUM_DOCKER_BUILDER.
                docker{
                    image 'maven:3.9.6-eclipse-temurin-17-focal'
                    //here we doing custome mapping so that container do not have to jars each time.
                    // so here we are mapping node_m2_path:container_m2_path as root user
                    args ' -u root -v /temp/m2:/root/.m2'
                }
            }
            steps{
                sh 'mvn clean package -DskipTests'
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

}