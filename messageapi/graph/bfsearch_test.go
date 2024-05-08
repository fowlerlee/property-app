package graph

import (
	"fmt"
	"testing"
)

func TestBasic(t *testing.T) {
	fmt.Println("testing basic")
	a := &Vertex{Name: "A"}
	b := &Vertex{Name: "B"}
	c := &Vertex{Name: "C"}
	d := &Vertex{Name: "D"}
	e := &Vertex{Name: "E"}

	a.List = append(a.List, b)
	a.List = append(a.List, c)

	c.List = append(c.List, d)
	d.List = append(d.List, e)

	// bfs := &BFS{}
	// bfs.traverse(*a)

	dfs := &DFS{}
	list := make([]*Vertex, 0)
	list = append(list, a, b, c, d)
	dfs.dfstraverse(list)

}
