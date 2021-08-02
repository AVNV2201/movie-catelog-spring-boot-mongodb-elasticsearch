# Movie Catelog using MongoDB and ElasticSearch

This Spring Boot Maven project is build with the purpose of learning Spring Boot with Spring Data MongoDB and Spring Data ElasticSearch     
The Web App throws REST APIs to maintain and querry the database.

## Contents

1. [Requirements](#requirements)
2. [Maven Dependencies](#maven-dependencies)
3. [Models](#models)
   1. [Movie](#movie-pojo)
   2. [Crew](#crew-pojo)
   3. [Person](#person-pojo)
4. [API Endpoints](#api-endpoints)
   1. [Movie](#movie)
   2. [Person](#person)

## Requirements
- MongoDB
- ElasticSearch
- Maven
- JDK
- IDE( IntelliJ )

## Maven Dependencies
The Maven dependencies used for this Spring Boot project are - 
- Spring Web
- Spring Data MongoDB
- Lombok
- Log4j2

## Models 
Models / POJO are - 
### Movie POJO
- id: String
- name: String
- description: String
- genre: enum
- releaseDate: Date
### Crew POJO
- id: String
- movieId: String
- personId: String
- characterName: String
- role: enum
### Person POJO
- name: String
- gender: enum
- id: String

## API Endpoints
### Movie
Method | Endpoint | Description
-------|----------|-------------
POST | /movie | Add a movie
GET | /movie | Get all movies 
GET | /movie?{genre} | get all movies of specific genre
GET | /movie/search?{q} | Search for movie with searchTerm q

### Person
Method | Endpoint | Description
-------|----------|-------------
POST | /person | Add a person
GET | /person/{id} | Get a person by its id
GET | /person | Get all persons
GET | /person?{gender}&{name} | Get list of persons using these optional querries

### Crew
Method | Endpoint | Description
-------|----------|-------------
POST | /crew | Add a crew
GET | /crew/{id} | Get a crew by id
GET | /crew/role/{role} | Get list of crew of particular role
GET | /crew?{movieId} | Get list of crew of particular movie
GET | /crew?{personId} | Get list of crew(all the roles played by a person)
PATCH | /crew/{id} | Update a crew by id and payload
DELETE | /crew/{id} | Delete a crew by id