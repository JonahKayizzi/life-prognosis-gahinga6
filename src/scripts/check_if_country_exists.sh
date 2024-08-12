country=$(awk -F ',' '{print $6}' life-expectancy.csv | grep -i $1)

if [ -n "$country" ]; then
   echo 1
else
    echo 0
fi