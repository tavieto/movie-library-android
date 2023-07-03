# Movie Library

Para utilizar o projeto é necessário criar um projeto firebase e adicionar o arquivo google-services.json na pasta do app, com Firebase Firestore criado com uma collection nomeada como "user" e o Firebase Authentication ativado com login por e-mail e senha.

Também será necessário o arquivo secrets.properties na raiz do projeto contendo as chaves da api TMDB com as seguintes nomenclaturas:

TMDB_URL_AUTH,
TMDB_API_BASE,
TMDB_API_BASE_URL_IMAGES,
TMDB_READ_TOKEN
