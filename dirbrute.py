import httplib
import fileinput
import time
from urlparse import urlparse
host = ''
f=open('results.txt','w')

directory = open('D:\TOOLS\DirBuster-0.12\DirBuster-0.12\directory-list-1.0.txt')
fuzz = directory.readlines()
wordlist = ()
for words in fuzz:
    words = words.strip()
    wordlist = wordlist + (words,)

for word in wordlist:
    time.sleep(1)
    url = urlparse('http://10.60.2.10:6001/'+word)
    conn = httplib.HTTPConnection(url.netloc)
    conn.request("HEAD", url.path)
    
    #url = 'http://{0}/{1}'.format(host, directory)
    print '     Trying: {0}'.format(word)

    response = conn.getresponse()
    print response.status
    if response.status == 200:
        print 'found-> '+word
        f.write(url.netloc+url.path+'\n')
    conn.close()
    
f.close()
        
