#!/bin/bash
touch test
tr '[A-Ma-mN-Zn-z]' '[N-Zn-zA-Ma-m]' < $1 > test
owner=`ls -l | grep $1 | cut -f3 -d ' '`
rm $1
mv test $1
chown $owner:$owner $1
