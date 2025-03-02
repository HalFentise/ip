# SmartNotes User Guide

## SmartNotes User ScreenShots
![ScreenShot of SmartNotes](Images\Screenshot1.png)

## SmartNotes Introduction
SmartNotes is an intelligent chatbot designed to help users efficiently manage and organize their important tasks.
With SmartNotes, you can easily create, update, and track tasks, deadlines, and events, all while enjoying a user-friendly interface.
The product leverages intuitive commands and natural language understanding, making it seamless to interact with. 

With features such as deadline tracking, event scheduling, task completion marking, and persistent storage, 
SmartNotes is your perfect companion for staying organized and productive.

## Adding Tasks

The main function of SmartNotes is task management, we have provided 3 types of tasks for you to use.
They have different features so you can choose one of them to best fit your task.
You will see the introduction of them below:

Please enter your command behind the following hint:
```angular2html
Enter command:
```

### 1. Todo
`todo` is a basic task type that stores simple tasks without any associated time information. 
It is ideal for tasks that do not have a specific deadline or schedule.

#### Usage:
To add a `todo` task, use the following command:
```
todo [task description]
```

#### Example:
```angular2html
todo Return book
```

#### Output:
Once you have added a `todo` task followed by the above introduction,
you will see the expected output below shown that our chatbot have successfully added your `todo` task:
```angular2html
Got it. I've added this task:  
  [T][ ] Buy groceries  
Now you have 1 task in the list.
```
The format of your task would be `[T][ ] task description`.
The first `[T]` represent that this task is a `todo` task.
The second `[ ]` shows that this task is not done. If you finish this task later, you can mark it as done.
Then the second `[ ]` will be shown as `[X]`. Mark function will be introduced in the next part.

#### Key Feature:
* **No time requirement:** You can use `Todo` for general tasks that do not need time tracking.

### 2. Deadline
`deadline` is a task type that stores tasks with a specific due date and time. 
It is perfect for tasks that must be completed by a certain deadline.

#### Usage:
To add a `deadline` task, use the following command:
```
deadline [task description] /by [date and time]
```
* **Date Format:** Acceptable formats include `yyyy-mm-dd`, `yyyy/mm/dd`, `dd/mm/yyyy`, or `dd-mm-yyyy`.
* **Time Format:** Use `HHmm` (24-hour format, e.g., 1800 for 6:00 PM).

**Notice:** You can also type in any format of date and time such as `next Friday`,
but the chatbot cannot understand other format, so it will just simply show the deadline is next friday.

#### Example:
```angular2html
deadline Submit assignment /by 2025-12-02 1800
```

#### Output:
The output of adding a `deadline` task is similar to adding a `todo` task.
```angular2html
Got it. I've added this task:
[D][ ] Submit report (by: Dec 2 2025, 6:00 PM)
Now you have 2 tasks in the list.
```
The format of your task would be `[D][ ] task description (by: date and time)`.
`[D]` represent that this task is a `deadline` task.
The `date and time` will be shown in the `MMM d yyyy, HH:mm am/pm` format.

#### Key Feature:
* **Time Required:** `deadline` must contain a time, no matter what format of time you are using.
If you do not want to add a time, you can try to create a `todo` task instead!
* **Time awareness:** The chatbot understands and formats the provided date and time for clarity.
* **Flexible input:** The system accepts multiple date formats for user convenience.

### 3. Event
`event` is a task type designed to store tasks or activities that have a start and end time.
It is ideal for scheduling events such as meetings, appointments, or celebrations.

#### Usage:
To add a `event` task, use the following command:
```
event [task description] /from [start date and time] /to [end date and time]
```
* **Date Format:** Acceptable formats include `yyyy-mm-dd`, `yyyy/mm/dd`, `dd/mm/yyyy`, or `dd-mm-yyyy`.
* **Time Format:** Use `HHmm` (24-hour format, e.g., 1800 for 6:00 PM).

**Notice:** Same as `deadline`, the format of date and time are not restricted, 
you can choose your own preferred format.

#### Example:
```angular2html
event Team meeting /from 2025-12-02 1400 /to 2025-12-02 1600
```

#### Output:
The output of adding an `event` is shown below:
```angular2html
Got it. I've added this task:
[E][ ] Team meeting (from: Dec 2 2025, 2:00 PM to: Dec 2 2025, 4:00 PM)
Now you have 3 tasks in the list.
```
The format of your task would be `[E][ ] task description (from: start date and time to: end date and time)`.
`[E]` represent that this task is an `event` task

#### Ket Feature:
* **Time Duration Required:** `event` task must contain a start time and end time.
* **Flexible date and time input:** Multiple formats are supported for user convenience.

## Enter command

To use SmartNotes to help manage your important tasks, we provide a set of useful commands that you can use to interact with the chatbot.
You can enter valid command behind the following hint:
```
Enter command: 
```

**Notice:** The command accept both upper case letter and lower case letter.

Below are the available commands and their functionalities:

### 1. List
`list` is a command that you can list all the tasks stored in SmartNotes.
You can also list a specific type of tasks using this command.

#### Usage:
To list all the tasks, you can use the following command:
```angular2html
list
```

#### Example:
Followed by adding 3 different tasks in the above instructions,
type in the following command:
```angular2html
list
```

#### Output:
The expected output shows below:
```angular2html
Here are your tasks:

1. [T][ ] Buy groceries 
2. [D][ ] Submit report (by: Dec 2 2025, 6:00 PM)
3. [E][ ] Team meeting (from: Dec 2 2025, 2:00 PM to: Dec 2 2025, 4:00 PM)
```

#### List one type of tasks:
Or you can use one of the following commands to list one specific type of tasks:
```angular2html
list todo
list deadline
list event
```

**Example:**
```angular2html
list deadline
```

**Output:**
```angular2html
Here are your tasks:

1. [D][ ] Submit report (by: Dec 2 2025, 6:00 PM)
```

### 2. Delete
`delete` is a command that you can use to delete one task from the list.

#### Usage:
To delete one task, you can use following command:
```angular2html
delete [index]
```

* `index` is the number of the task in the list, you can find it using `list` command,
the number in front of the task is the task index.

#### Example:
```angular2html
delete 2
```

### Output:
Once the chatbot delete the task from the list, you will find the expected output below:
```angular2html
Noted. I've removed this task:
[D][ ] Submit report (by: Dec 2 2025, 6:00 PM)
Now you have 2 tasks in the list.
```

### 3. Mark & Unmark
Mark and unmark have been mentioned above.
These two function is to help you mark your task as done and finished.
If you find one task has not done, but you accidentally marked it as done, you can just unmark it.

#### Usage:
To mark or unmark a task, you can use following commands:
```angular2html
mark [index]
unmark [index]
```

* Same as `delete` command, the index is the number of the task in the list.

#### Example:
Type in these two command in sequence:
```angular2html
mark 1
unmark 1
```

#### Output:
The expected output of command `mark 1` would be:
```angular2html
Great! I've marked this task.
[T][X] Buy groceries
```
The expected output of command `unmark 1` would be:
```angular2html
Ok, I've unmarked this task.
[T][ ] Buy groceries
```

### 4. Find
`find` is a command that can be used for finding the specific tasks.
You can find specific tasks that the task description contains your finding keyword.

#### Usage:
To find a specific task, you can use following command:
```angular2html
find [description]
```

* `description` is the keyword that your target tasks should contain.
In other word, only task description contain this keyword then this task will be found to you.

#### Example:
Suppose we want to find tasks related to buying something, we can use `find` command:
```angular2html
find Buy
```

#### Output:
The expected output is shown below:
```angular2html
Here are your tasks:

1. [T][ ] Buy groceries
```

## Exit

To exit SmartNotes, you can use the following command:
```angular2html
Bye
```

## Saving Data
Our data is auto saved, so you do not need to do anything to save it.
Your task list will be saved once you have made any changes to it.

Your saved data will be automatically loaded to the program when you turn on SmartNotes.
You can check whether it has any mistake with your previous progress using command `list`.

You can access to the saved data use the following path:\
`SmartNotes_jar/data/list.txt`

The format of data would be:
```angular2html
T | Not Done | have dinner
D |   Done   | submit report (by: 2025-01-28)
```

**Notice:** It is not suggested to modify data directly inside the file `list.txt`.
If you have to change data in `list.txt`, please follow the same format and keep the number of `space` same.
Or it will cause error when loading your data to SmartNotes.

If you have already caused some loading error by changing the file format,
please delete all the data in the file `list.txt`, and added them again in SmartNotes.

## Notes

- Ensure all commands are consistent within a single command.
- Unrecognized formats will be treated as plain text.
- If any required details are missing or incorrectly formatted,
the system will prompt an error.
- Modify the data file is not suggested
- If any error has occurred by changing the data file, please delete everything inside the file to fix the error.

### Thanks for using our product! Happy task managing!