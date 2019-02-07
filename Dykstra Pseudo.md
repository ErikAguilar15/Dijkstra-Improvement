# Dykstra-Improvement-
source: http://www.gitta.info/Accessibiliti/en/html/Dijkstra_learningObject1.html

Dijkstra Algorithm: Short terms and Pseudocode
Using the Dijkstra algorithm, it is possible to determine the shortest distance (or the least effort / lowest cost) between a start node and any other node in a graph. The idea of the algorithm is to continiously calculate the shortest distance beginning from a starting point, and to exclude longer distances when making an update. It consists of the following steps:

Initialization of all nodes with distance "infinite"; initialization of the starting node with 0
Marking of the distance of the starting node as permanent, all other distances as temporarily.
Setting of starting node as active.
Calculation of the temporary distances of all neighbour nodes of the active node by summing up its distance with the weights of the edges.
If such a calculated distance of a node is smaller as the current one, update the distance and set the current node as antecessor. This step is also called update and is Dijkstra's central idea.
Setting of the node with the minimal temporary distance as active. Mark its distance as permanent.
Repeating of steps 4 to 7 until there aren't any nodes left with a permanent distance, which neighbours still have temporary distances.
1:	function Dijkstra(Graph, source):
2:	for each vertex v in Graph:	// Initialization
3:	dist[v] := infinity	// initial distance from source to vertex v is set to infinite
4:	previous[v] := undefined	// Previous node in optimal path from source
5:	dist[source] := 0	// Distance from source to source
6:	Q := the set of all nodes in Graph	// all nodes in the graph are unoptimized - thus are in Q
7:	while Q is not empty:	// main loop
8:	u := node in Q with smallest dist[ ]
9:	remove u from Q
10:	for each neighbor v of u:	// where v has not yet been removed from Q.
11:	alt := dist[u] + dist_between(u, v)
12:	if alt < dist[v]	// Relax (u,v)
13:	dist[v] := alt
14:	previous[v] := u
15:	return previous[ ]
Top	
