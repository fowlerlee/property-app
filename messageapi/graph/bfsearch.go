package graph

import (
	"fmt"
)

type Vertex struct {
	Name    string
	Visited bool
	List    []*Vertex
}

type BFS struct {
	Queue []*Vertex
}

func (b *BFS) traverse(root Vertex) {
	q := []Vertex{}
	root.Visited = true
	q = append(q, root)

	for len(q) > 0 {
		vertex := q[0]
		q := q[1:]
		fmt.Println("visited vertex: ", vertex)
		for _, vertex := range vertex.List {
			if !vertex.Visited {
				vertex.Visited = true
				q = append(q, *vertex)
			}
		}
	}
}
