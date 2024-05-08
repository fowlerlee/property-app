module github.com/fowlerlee/property-app/messageapi

go 1.22.0

require (
	github.com/golang-jwt/jwt/v4 v4.5.0
	github.com/gorilla/mux v1.8.1
	github.com/lib/pq v1.10.9
	github.com/rabbitmq/amqp091-go v1.9.0
	golang.org/x/crypto v0.22.0
	google.golang.org/grpc v1.63.2
)

require github.com/golang-collections/collections v0.0.0-20130729185459-604e922904d3 // indirect

require (
	golang.org/x/net v0.21.0 // indirect
	golang.org/x/sys v0.19.0 // indirect
	golang.org/x/text v0.14.0 // indirect
	google.golang.org/genproto/googleapis/rpc v0.0.0-20240227224415-6ceb2ff114de // indirect
	google.golang.org/protobuf v1.33.0
)
