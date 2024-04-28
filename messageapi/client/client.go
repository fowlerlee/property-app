package main

import (
	"context"
	"flag"
	"log"
	"time"

	api "github.com/fowlerlee/property-app/messageapi/proto"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

const (
	port = ":3200"
)

func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}

const (
	defaultLog = "logEmpty"
)

var (
	addr = flag.String("addr", "localhost:3200", "the address to connect to")
	name = flag.String("name", defaultLog, "Log message")
)

func main() {

	flag.Parse()
	// Set up a connection to the server.
	conn, err := grpc.NewClient(*addr, grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()
	c := api.NewLogClient(conn)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	r, err := c.Consume(ctx, &api.ConsumeRequest{Offset: 12})
	if err != nil {
		log.Fatalf("could not greet: %v", err)
	}
	log.Printf("Greeting: %s", r.GetMessage())

	r, err = c.Produce(ctx, &api.ProduceRequest{Record: })
	if err != nil {
		log.Fatalf("could not greet: %v", err)
	}
	log.Printf("Greeting: %s", r.GetMessage())

}
