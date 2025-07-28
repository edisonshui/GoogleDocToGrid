GoogleDocs Grid Renderer
This Java program fetches a table from a Google Docs document, interprets it as character coordinate data, and prints the result as a 2D character grid in the console.

Table Format
The input Google Doc must contain a table with three columns:
1. x-coordinate (integer)
2. Character (single visible character, such as █, ▀, etc.)
3. y-coordinate (integer)

Example:
x-coordinate | Character | y-coordinate
0            | █         | 0
1            | ▀         | 1

Prerequisites
- Java 17 or higher
- Maven
- A Google Cloud project with the Google Docs API enabled
- A client_secret.json file downloaded from Google Cloud

Project Structure
src/
├── org.example/
│   ├── Main.java
│   ├── GoogleDocService.java
│   ├── GridPrinter.java
│   ├── TableEntry.java
│   └── TableFetcher.java

Concepts Covered
Google Docs API integration
OAuth 2.0 authorization flow in Java
Parsing structured document content
2D grid rendering and coordinate mapping
Maven project structure and dependency management
