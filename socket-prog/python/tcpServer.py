from socket import *
port = 8000
ssocket = socket(AF_INET, SOCK_STREAM)
ssocket.bind(('',port))
ssocket.listen(1)
print 'i am ready'
while 1:
	conn, addr=ssocket.accept()
	sent = conn.recv(1024)
	modsent=sent.upper()
	conn.send(modsent)
	conn.close()

