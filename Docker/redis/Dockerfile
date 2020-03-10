#use an existing docker image as a base
FROM alpine
# Download and install a dependency
RUN apk add --update redis
# Tell the image what to do when it starts as a container
CMD ["redis-server"]

# run the below command
#docker build.
# Create your own image/ tagging image  : spaduri image
# docker build -t spaduri/redis:latest .