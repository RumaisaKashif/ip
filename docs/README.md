# Pook User Guide
!["Product screenshot"](Ui.png)

Pook is a command-line task management chatbot that helps you manage tasks and notes efficiently.

Here we've detailed how to use Pook to manage your notes and tasks. Happy chatting!

## Adding deadlines

Adds a task with a deadline.
### Format
```
deadline <description> /by <date/time>
```
### Example 
```
deadline submit assignment /by 2026-03-01 23:59
```
### Expected Output
Task added to tasks file and can be viewed through ```list```

## Adding events
### Format
```
event <description> /from <date/time> /to <date/time>
```
### Example 
```
event hari raya puasa /from 2026-03-21 23:59 /to 2026-03-22 23:59
```
### Expected Output
Task added to tasks file and can be viewed through ```list```

## Adding todos
### Format
```
todo <description>
```
### Example 
```
todo watch Wuthering Heights
```
### Expected Output
Task added to tasks file and can be viewed through ```list```

## Viewing a list of tasks
### Format
```
list
```
### Expected Output
A list of all saved tasks with their types and completion statuses reflected.
E.g. ```list```
```
1. [D][X] submit cs2103t lab (by: 2nd December 2026)
2. [T][] read book
```

## Marking tasks as done
### Format
```
mark <index>
```
### Example 
```
mark 2
```
### Expected Output
Task marked as done and completion status updated. It can be viewed through ```list```.

E.g. ```mark 1```
Before marking as done
```
1. [T][] Read book
```
After marking as done
```
1. [T][X] Read book
```

## Unmarking tasks as not done yet
### Format
```
unmark <index>
```
### Example 
```
unmark 2
```
### Expected Output
Task unmarked and completion status updated. It can be viewed through ```list```.

E.g. ```unmark 1```
Before unmarking
```
1. [T][X] Read book
```
After unmarking
```
1. [T][] Read book
```

## Finding tasks containing a specific keyword 
### Format
```
find <keyword>
```
### Example 
```
find submit
```
### Expected Output
All tasks with descriptions containing the word "submit" will be retrieved and displayed as a list.

## Adding notes
### Format
```
note add <content>
```
### Example 
```
note add need to provide measurements for grad gown
```
### Expected Output
Note saved to notes file and can be viewed through ```notes list```

## Listing all notes
### Format
```
note list
```
### Example 
```
note list
```
### Expected Output
All saved notes listed in chat

E.g.
```
1. need to provide measurements for grad gown
2. buy cheddar
```

## Deleting notes
### Format
```
note delete <index>
```
### Example 
```
note delete 1
```
### Expected Output
Note deleted from notes file and can be checked through ```notes list```

## Exiting the application
### Format
```
bye
```
### Expected Output
Application closes after exit message is displayed.