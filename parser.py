#!/usr/bin/python

import xml.etree.ElementTree as ET
import sys
tree = ET.parse('Checkmarx-Report.xml')
root = tree.getroot()
#print root;

vuln = "Vulneravility_name"
vuln_no = 10;#number of instances of the vulnerability

flag = 0
i=-1

def writer(value):
	with open('instances.txt','a') as ffile:
		ffile.write(value+'\n');

def writer2(value):
	with open('instances_files.txt','a') as ffile:
		ffile.write(value);

def writer3(value):
	with open('instances_lines.txt','a') as ffile:
		ffile.write(value);

for vulns in root.findall('Query'):
	i=i+1
	vulnerability = vulns.get('name')
	print vulnerability
	if (vulnerability==vuln):
		break;

print i
j=0;
prev_file=""
instance_no=0;
b=-1;

#a=[3,4,5,14,15,25,27,28,42,43,46,48,51,55,56,57,58]
while (j != vuln_no):
	print "Instance No.:",j+1
	for instance in root[i][j][0].findall('PathNode'):
	#print instance
		#print pathnodes
		files = instance.find('FileName').text;
		lineNumber = instance.find('Line').text
		snippet = instance.find('Snippet')[0][1].text
		print files
		#if (j+1) in a:
		#	writer("Instance no.:"+str(j+1)+" - "+snippet+"#Not Reported")
		#else:
		#	if (prev_file!=files):
		#		prev_file=files
		#		instance_no=instance_no+1
		#		writer2('\n'+str(instance_no)+". "+files)
		#		writer3('\n'+str(instance_no)+". "+lineNumber)
		#	else:			
		#		writer3(', '+lineNumber)
		print lineNumber
		print snippet
		#writer(files)
		#writer(lineNumber)
		if (j!=b):
			writer("Instance no.:"+str(j+1)+" - "+snippet)
			b=b+1
		else:
			writer(snippet)
		writer2('\n'+str(j+1)+". "+files)
		writer3('\n'+str(j+1)+". "+lineNumber)
	j=j+1

print "Instances Counted", j;
