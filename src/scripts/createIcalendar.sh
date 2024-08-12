#!/bin/bash
eventdate=$1
person=$2
# Define event details
EVENT_START="$eventdate.T100000Z"  # Example start date in UTC
EVENT_END="$eventdate.T110000Z"    # Example end date in UTC
SUMMARY="Expected date of demise"
DESCRIPTION="For $person"

# Create the .ics content
ICS_CONTENT="BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//Life Prognosis//Healthcare APP//EN
BEGIN:VEVENT
UID:$(date +%Y%m%dT%H%M%SZ)@yourdomain.com
DTSTAMP:$(date +%Y%m%dT%H%M%SZ)
DTSTART:${EVENT_START}
DTEND:${EVENT_END}
SUMMARY:${SUMMARY}
DESCRIPTION:${DESCRIPTION}
END:VEVENT
END:VCALENDAR"

# Write the content to an .ics file
echo "$ICS_CONTENT" > event.ics

echo "ICS file created successfully."