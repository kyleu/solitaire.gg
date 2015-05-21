# Scalataire

Almost certainly the web's finest card games.

## Running the app

Create a DNS or hosts entry for "solitaire.dev", pointing to your server.

```shell
$ sbt
> run
$ open http://solitaire.dev
```

## Projects

* `scalataire` Main web application. Handles online games.
* `sharedJvm` Core game logic and rules definitions, for JVM projects.
* `sharedJs` Shared classes, compiled to Scala.js JavaScript.
* `client` Bare-bones Scala.js app to support offline play.
* `util` Various utility projects, mainly for batch processing.


## Technology

Solitaire.gg is a WebGL client-server solitaire implementation with offline support.

In online mode, a Play application communicates over a WebSocket to a pool of Akka actors managing games and connections. 
Serialization is handled by Play Json, and all database communication runs via postgres-async. In offline mode, Scala.js compiles the
shared code and provides an in-browser server, communication with the client via JavaScript interop.

Rendering is handled by Phaser.io, and you can register with Facebook, Google, Twitter, or Steam thanks to Play Silhouette.

## Contributing

All code is formatted by Scalariform, and passes all tests from Scalastyle and Scapegoat. No Scala file is longer than 100 lines, no line 
longer than 140 characters, and all warnings are treated as errors. Tests are part of the main source tree so they can be run from the browser. 
