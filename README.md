# Solitaire.gg

Almost certainly the web's finest card games.

## Running the app

Create a DNS or hosts entry for "solitaire.dev", pointing to your server.

```shell
$ sbt
> run
$ open http://solitaire.dev
```

## Projects

* `solitaire-gg` Main web application. Handles online games.
* `sharedJvm` Core game logic and rules definitions, for JVM projects.
* `sharedJs` Shared classes, compiled to Scala.js JavaScript.
* `client` Bare-bones Scala.js app to support offline play.
* `util` Various utility projects, mainly for batch processing.


## Technology

Solitaire.gg is a WebGL client-server solitaire implementation with offline support.

In online mode, a Play application communicates over a WebSocket to a pool of Akka actors managing games and connections. 
Serialization is handled by Play Json, and all database communication runs via postgres-async. In offline mode, Scala.js compiles the
shared code and provides an in-browser server, communicating with the client via JavaScript interop.

Rendering is handled by Phaser.io using a simple message-passing interface.
You can register with Facebook, Google, Twitter, or Steam thanks to Play Silhouette.


## Metrics

All meaningful operations are tracked through Scala Metrics, and are exposed through JMX or a servlet available by default on port 9001.
Reporting to Graphite can be enabled through application.conf, and reports to localhost:2003 by default.
Metrics exposes all actors, queries, logs, requests, and jvm info.


## Contributing

All Scala code is formatted by Scalariform, and passes all tests from Scalastyle and Scapegoat. No Scala file is longer than 100 lines, no line 
longer than 140 characters, and all warnings are treated as errors. Tests are part of the main source tree so they can be run from the browser.

JavaScript is verified by Require.js and UglifyJS. Any Javascript errors or warnings will be treated as compile errors. 

Security filters are in place, and are configured so that resources (css, js, etc) can only be loaded from the configured host.
Inline styles and scripts are prohibited.


## License

Copyright 2015 Kyle Unverferth
All rights reserved, for now.
