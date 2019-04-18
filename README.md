# Dijkstra's Algorithm

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

To tackle our problem we will be using 3 Classes Graph, Edges, and Nodes.
## Set up
    - Create a Graph
        - This is were we will store all our nodes.
    - Create Nodes
        - These will be used to be inserted into the graph.
    - Create edges 
        - These will be created by passing src&dst nodes along with a length
        - In the case of the tank we can also just include a nodeID
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
  - Created a running Dijkstra with given tank name or src&dst ports
  - Can print nodes and edges as if they were strings to facilitate inserting to database
  - 

## TO DO:
  - Need to connect to database to check with larger networks
  - Create method to take in server input or implement a method of stress testing with larger node networks, 
  - Need method to print alternate path to destination
    - Do we need to remove paths and rerun dijjktra's algorithm or do we just look for paths based on what is already traversed 
    - Possibly resetting last few iterations of Dijkstra's and reruning (adding nodes back to frontier and resetting their costs)   
  - We need to be able to grab data from SQL and create an automation function that will input all Node (Pipe) Data and Edge (Clip and Hose) Data


## Pseudo Code
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
