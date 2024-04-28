# Welcome to the golang api for the property app

The repo contains:
    - gRPC connections between services
    - REST api
    - Postges persistance
    - JWT authentication



## Setup locally

```bash
$ go install google.golang.org/protobuf/cmd/protoc-gen-go@v1.28
$ go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@v1.2

$ export PATH="$PATH:$(go env GOPATH)/bin"
```


## Generate Proto files

```bash
 protoc --go_out=. --go-grpc_out=. proto/message.proto

```
