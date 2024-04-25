# Property App repo for apartment sales

# Contents

- springbackend - Spring Framework
  Java Spring with SQL to postgres

- Postgres - dockerized
  Holds User info and file upload for images and video

- Redis
  Dockerized for use of the Redis Distributed Lock using Spring

- AwesomeProject - React Native
  Frontend client for interacting with the Property App data

- Python ML/AI app - pythonAnywhere

# End points

## Users

- GET /api/vi/person

- POST /api/vi/person/{Person}

- DELETE /api/vi/person/{uuid}

- UPDATE /api/vi/person/{id, Person}

- POST /auth/register

- POST /auth/authenticate

## Attachments with 200KB limit

- GET /viewall - only displays 3 images at a time

- POST /upload/{File}

- DELETE /delete/{id}

- UPDATE /update/{id, File}

- DOWNLOAD /download/{fileId}
