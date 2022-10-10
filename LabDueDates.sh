#! /usr/bin/bash

# Today's date
today=`date +%s`

#Lab dates
lab1=`date -d '2022-09-28 15:30' +%s`
lab2=`date -d '2022-10-26 15:30' +%s`
lab3=`date -d '2022-11-23 15:30' +%s`
lab4=`date -d '2022-12-07 15:30' +%s`

function print_lab() {
	difference=$(($2 - $today))
	format='%m/%d/%Y at %_I:%M%p'
	title="$1 `date -d @$2 +"($format): "`"
	if [ $difference -lt 0 ]; then
		message='Pass Due'
	else
		seconds="`expr $difference % 60` Seconds"
		minutes="`expr $difference / 60 % 60` Minutes"
		hours="`expr $difference / 60 / 60 % 24` Hours"
		days="`expr $difference / 60 / 60 / 24` Days"
		message="$days $hours $minutes $seconds Remaining"
	fi
	echo $title $message
}

print_lab 'Lab 1' $lab1
print_lab 'Lab 2' $lab2
print_lab 'Lab 3' $lab3
print_lab 'Lab 4' $lab4
