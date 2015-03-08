from socket import *
sname='localhost'
sport=8000
csocket = socket(AF_INET,SOCK_STREAM)
csocket.connect((sname,sport))
sentence=raw_input('enter a sentence: ')
csocket.send(sentence)
mod = csocket.recv(1024)
print mod
csocket.close()

