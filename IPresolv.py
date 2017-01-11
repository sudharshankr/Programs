import sys
import socket

#f = open('hosts','r')
f1 = open('hosts_with_ips','w')
f2 = open('ips','w')
errorfile = open('errorlog','w')
hostfile = sys.argv[1]

with open(hostfile,'r') as f:
 for host in f:	
	try:
		ip_array = socket.gethostbyname_ex(host.strip())
		print ip_array
		f1.write(str(ip_array))
		f1.write("\n")
		ip = socket.gethostbyname(host.strip())
		print ip
		f2.write(ip)
		f2.write("\n")
	except:
		print "Oops! Something went wrong with the hostname -> "+host.strip()
		errorfile.write(host) 

f1.close()
f2.close()
print "Done! Check out the files: hosts_with_ips and ips for stored results"
print "Check errorlog in case of any errors"
errorfile.close()