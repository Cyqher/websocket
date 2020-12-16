sudo su root

echo "sending dir"
rm -rf /home/websocket/docker
mv ./target/docker /home/websocket

echo "making image"
docker build -t websocket:release /home/websocket/docker

echo "stop present container"
chmod 777 /home/stop.sh
/home/stop.sh

echo "runing container"
docker run -d -p 8091:8091 --name="websocket" websocket:release