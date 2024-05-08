package graph

import "fmt"

type DFS struct {
	Stack []*Vertex
}

func dfsHelper(root Vertex) {
	q := []Vertex{}
	root.Visited = true
	q = append(q, root)

	for len(q) > 0 {
		vertex := q[len(q)-1]
		q = q[:len(q)-1]
		fmt.Println("visited vertex: ", vertex)
		for _, v := range vertex.List {
			if !v.Visited {
				v.Visited = true
				q = append(q, *v)
			}
		}
		if !vertex.Visited {
			vertex.Visited = true
			q = append(q, vertex)
			fmt.Println("visited vertex: ", vertex)
		}
	}
}

func (dfs *DFS) dfstraverse(root []*Vertex) {
	for i := 0; i < len(root[i].List); i++ {
		if !root[i].Visited {
			root[i].Visited = true
			dfsHelper(*root[i])
		}
	}
}
