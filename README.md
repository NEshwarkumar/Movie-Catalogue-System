# Movie Catalogue System

This is a Java Spring Boot web application that allows users to browse, search, and save their favorite movies using data from the TMDb API. The app features a modern dark-themed interface and leverages REST API integration, Thymeleaf templating, and an H2 in-memory database for managing user favorites.

## Features

### Main View
- Displays trending movies from the TMDb API
- Search bar to look up specific movie titles
- Each movie card includes:
  - Title
  - Poster
  - Overview
  - View Details button
  - Add to Favorites button

### Movie Detail View
- Detailed movie information including:
  - Title
  - Poster
  - Release date
  - Description
  - Rating
- Option to add the movie to favorites

### Favorites Page
- Displays a list of all user-favorited movies
- Each favorite shows the movie poster and title
- Remove from favorites button

## Technologies Used

- Java 17
- Spring Boot
- Thymeleaf
- RestTemplate
- H2 In-Memory Database
- Gradle


