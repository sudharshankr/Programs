from socket import *
sport = 8001
sname = 'localhost'
csocket = socket(AF_INET, SOCK_DGRAM)
message=raw_input('enter the mmessage here: ')
csocket.sendto(message, (sname,sport))
mod, serverAddr = csocket.recvfrom(2048)
print mod
csocket.close()

