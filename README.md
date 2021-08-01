# Movie Catelog using MongoDB

This Spring Boot Maven project is build with the purpose of learning Spring Boot and Spring Data MongoDB  
The Web App throws REST APIs to maintain the database.

## Contents

1. [Requirements](#requirements)
2. [Maven Dependencies](#maven-dependencies)
3. [Models](#models)
   1. [Movie](#movie-pojo)
   2. [Crew](#crew-pojo)
   3. [Person](#person-pojo)
4. [API Endpoints](#api-endpoints)
   1. [Person](#person)

## Requirements
- MongoDB
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
### Crew POJO
### Person POJO
- name: String
- gender: enum
- id: String

## API Endpoints
### Person
Method | Endpoint | Description
-------|----------|-------------
POST | /person | Add a person
GET | /person/{id} | Get a person by its id
GET | /person | Get all persons
GET | /person?{gender}&{name} | Get list of persons using these optional querries
