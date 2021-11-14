#!/bin/sh

values=""
echo "Enter program input: (Empty line followed by ENTER executes the program with the given input)"
while read line
do
  # break if the line is empty
  [ -z "$line" ] && break
  values+="$line,"
done

params=""
if [ -n "$values" ]; then
	params="lines=${values}"
	params=${params::-1}
fi

curl -s "http://localhost:8080/play" --data-urlencode "$params"
