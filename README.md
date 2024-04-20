# Dictonary Client

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a simple GUI-based dictionary client implemented in Java for connecting to a Multi-Threaded TCP Dictionary Server. It allows users to input words and receive their meanings from the server.

## Usage

Enter a word in the input field and press the "Send" button, to query the server for its meaning or simply press enter on your keyboard.

The server's response will be displayed in the output area of the GUI.

## Requirements

- Java
- Swing GUI library
- Gradle

## Build and Run

1. Clone the repository:

```bash
git clone https://github.com/HamzaRadaideh/Multi-Threaded-Dictonary-server-client-side
cd dictionary-server
```

2. Run the server:

```bash
gradle run
```

## Run the server

> Ensure the Multi-Threaded TCP Dictionary Server is running and accessible before using the client.
> The client GUI automatically attempts to connect to the server on startup.

## Server Configuration

- By default, the client assumes the server is running on 127.0.0.1 (localhost) and listening on port 8888. Modify SERVER_IP and SERVER_PORT in ServerConnection.java if needed.

## Structure

- `app/src/main/java/org/example/`
  - `App.java`: Main class containing server logic.
  - `DictionaryGUI.java`: Implements the GUI for user interaction.
  - `ServerConnection.java`: Handles communication with the server.

# License

This project is licensed under the [MIT License](./LICENSE).
