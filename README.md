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
V := Vertices
E := Edges

Graph(V, E)

 1    void  Dijkstra(Graph, Source) {
 
 2      Source.setCost(0)
 3      E := Expanded
 4      F := Fronteir
 
 5      E {}
 6      F { Source }
 
 7      while F Not Empty {
 8          cv := Current Vertex 
 9          cv =  min(Edges)
 
 10         F.remove(cv)
 
 11         for each n in cv.Neighbors {
 12             n := neighbor adjacent vertex
 13             cost := edge.cost
 14             if av not in V
 15                 find findShortcut(cv, av, cost);
 16             F.add(av)
 17         }
 18     E.add
 19     }
 20   }

```

License
----

MIT

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
