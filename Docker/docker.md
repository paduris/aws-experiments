


    docker version
    docker run hello-world
    docker run <image-name> <override command>
        docker run busybox ls
        docker run busybox echo hello
    
    docker ps   
    docker ps --all
    
    docker run = docker create + docker start
    
    docker create hello-world
    docker start -a hello-world
    
    
    docker system prune
    
    docker logs <container-id>
    
    Stop Container
        stop -> SIGTERM -> shutdown the containter on its own time ( waits for 10 secs to issue a kill command)
        docker stop <container-id> 
        
    Kill a Container
        kill -> SIGKILL -> stop right now 
        docker kill <containter-id>
    

     Execute an additional command in a container
        it : Allows us to provide input to the container
     docker exec -it <container_id> <command>
        sh: shell - command processor [bash,powersheel,zsh,sh]
     docker exec -it <container-id> sh

     --very less used : sh with run command-- 
     docker run -it busybox sh
     
     # building a redis image 
     docker build .




