from socket import *
port = 8001
udps = socket(AF_INET,SOCK_DGRAM)
udps.bind(('',port))
while 1:
	message, caddr = udps.recvfrom(2048)
	mod = message.upper()
	udps.sendto(mod, caddr)
	
