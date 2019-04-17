# Dijkstra's Algorithm

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

To tackle our problem we will be using 3 Classes Graph, Edges, and Nodes.
- Set up
    - Create a Graph
        - This is where we will store all our nodes.
    - Create Nodes
        - These will be used to be inserted into the graph.
    - Create edges 
        - These will be created by passing src&dst nodes along with a length
- Run Time
    - Set Source and Destination
    - Calculates shortest distance
    
## Class definitions
- Node our Pipe (2 cp)
        - Integer distanceFromSrc = INF
        - String IN_SYS_I
        - String OUT_SYS_I
        - Integer pipeLength
- Edges our Connection (Clips or Hoses)
        - Node Pipe_from
        - Node Pipe_to

## New Updates!
  - Created a running Dijkstra with generic Nodes
  - Need to work on implemeting our algorithm to regocognize the different type of edges in our system.


## TO DO:
  - Import and save files from GitHub, Dropbox, Google Drive and One Drive
  - Drag and drop markdown and HTML files into Dillinger
  - Export documents as Markdown, HTML and PDF


#### Pseudo Code
```
 1  int[] Dijkstra(source) 
    {
 2     for each vertex v            	     // Initializations
       {
 3         dist[v] := infinity               // Unknown distance function from s to v
 4         previous[v] := -1		     // the array previous stores parent info.
 5         Q.insert(v)			     // Insert all vertices into the priority queue Q
                                             // The dist-values are used as priorities
       }

 6     dist[source] := 0                     // Distance from source to source is set to 0
 7     Q.change(source, 0)                   // and this is updated in the priority queue as well

 8     while Q is not empty:                 // The main loop
       {
 9         u := Q.delete()                   // Remove best vertex from priority queue; returns source on first iteration
10         for each neighbor v of u:         // where v has not yet been considered
           {
11             alt = dist[u] + weight(u, v)
12             if alt < dist[v]              // Relax (u,v)
               {
13                 dist[v] := alt
14                 previous[v] := u
15	           Q.change(v, dist[v]) 
	       }
	   } // end of for-each-neighbor
       } // end of while-Q-is-not-empty
16     return previous[]
    } // end of function

```

License
----

MIT

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
