# QueueApp
Application made for Visma programming test.
The test initially asked for Docker implementation, but as I had never used the software before,
I eventually decided on a different approach to the task.

The application fetches a queue of vacation requests from a database, and stores the data in an Arraylist.
I chose to store the data in a list, as I figured if was easier to work with the data this way.
The application sends a request to the list to get the next request in the queue. From here you can either
approve or decline the request. The approved requests gets stored in an approved-list, while the declined requests
gets stored in a request log. I added a couple buttons so the user can view the logs in the "console" in the app.
