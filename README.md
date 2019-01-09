# MKS21X-FinalProject



## :memo: Development Logs  
### DAY 1 - Jan 3, 2019
- **Grace** :octocat:
	- wrote in the fields and methods from our UML into the OurCalendar class
	- wrote in the fields from our UML into the Event class
	- made a bunch of extra (unnecessary) commits during the process of learning branching
	- many of these are merges for figuring out how to update each branch
- **Tammy** :sleepy:
	- wrote in method templates for Event class
	- learned about README.md and the formatting (made way too many unneccessary commits)
	- learned about branching and figured out merge conflicts were a thing not to play around with
### DAY 2 - Jan 4, 2019
- **Grace** :octocat:
	- created CalendarViews class with empty methods and fields
	- wrote numberOfDays to calculate how many days from Jan 1 2019 a specific day is
	- using numberOfDays, wrote getFirstDayOfMonth (these will be useful for printing out calendars
	- tested both in temporary main in CalendarViews, debugged (will test more later on)
- **Tammy** :hatched_chick:
	- finished the template for Event class + fix changes on prototype
	- filled in all the accessors/set methods in Event class
	- played around with lanterna and the jar file
### DAY 3 - Jan 5, 2019
- **Grace** :octocat:
	- added exceptions to numberOfDays
	- wrote getFirstDayOfYear, getWeekday
	- added private method that converts number 0-6 to the weekday (will be useful in other methods as well)
	- filled in one-line get and set methods
	- deleted getFirstDayOfWeek (would not have been useful)
- **Tammy** :smile:
	- fix prototype
	- fell asleep in the middle of doing the project :cry:
### DAY 4 - Jan 6, 2019
- **Grace** :octocat:
	- learned CSV stuff about writing, appending, and reading csv files
	- made changes to prototype
	- wrote getStartOfWeek that returns the date week #_ starts on (might be what getFirstDayOfWeek should have been)
	- note to self: many methods in CalendarViews need EXCEPTIONS :rotating_light:
- **Tammy** :sweat:
	- finished making changes to the prototype (more to come!)
	- learned about CSV reading and appending in java
		- realized Scanner was used for .txt files
	- tested a main to see how to read and write in new columns and rows
### DAY 5 - Jan 7, 2019
- **Grace** :octocat: (Grace struggled today :persevere:)
	- created Date class to enclose all generic methods such as convertToDay
	- extended Date in CalendarViews
	- wrote getData in CalendarViews (not yet tested)
- **Tammy** :disappointed_relieved:
	- learned about asking for user input
	- wrote Event class constructor
		- made two constructors: one with all parameters, one empty
	- made Driver to test user input
		- implemented Event class and test constructor
### DAY 6 - Jan 8, 2019
- **Grace** :octocat:
	- added more useful methods to Date class, such as isLeapYear and daysInMonth
	- wrote printMonth without event dots in CalendarViews
	- condensed numberOfDays with new methods
	- added system of dots to printMonth using demo csv file
- **Tammy** :relieved:
	- put in a couple of new methods for Event class 
	- fix Driver so now it can print out all the information of the event given user input 
	- put in csv handler in Driver so Events can now be added into a CSV file
		- for now, events are stored in life.csv 
	- made changes to prototype AGAIN! :no_mouth:
	
### DAY 7 - Jan 9, 2019
- **Grace** :octocat:

- **Tammy** 
	- [ ] take into consideration all the user input for Time in the Driver (EXCEPTIONS!)
		- make sure length is no more than 5 
		- search through string for : '/ split it / check to see if each individual char is a number  
	- [ ] take into consideration AM and PM (or we could do military time)
