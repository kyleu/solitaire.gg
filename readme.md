# Solitaire.gg

https://solitaire.gg

## Running the app

```shell
$ sbt
> run
$ open http://localhost:9000
```

## Projects

* `solitaire-gg` Main web application. Handles online games.
* `sharedJvm` Core game logic and rules definitions, for JVM projects.
* `sharedJs` Shared classes, compiled to Scala.js JavaScript.
* `sharedNative` Shared classes, compiled to native code.
* `client` Scala.js app to support offline play.
* `util` Various utility projects, mainly for batch processing.


## Technology

Solitaire.gg is a WebGL client-server solitaire implementation with offline support. Using 4K-ready retina-quality HD graphics, Solitaire.gg aims to be the best card game available.

In online mode, a Play application communicates over a WebSocket to a pool of Akka actors managing games and connections. 
Serialization is handled by Play Json, and all database communication runs via postgres-async. In offline mode, Scala.js compiles the
shared code and provides an in-browser server, communicating with the client via JavaScript interop.

Rendering is handled by Phaser.io using a simple message-passing interface.
A general Solitaire solver is included, supporting backtracking and propagation.


## Metrics

All meaningful operations are tracked through Scala Metrics, and are exposed through JMX or a servlet available by default on port 9001.
Reporting to Graphite can be enabled through application.conf, and reports to localhost:2003 by default.
Metrics exposes all actors, queries, logs, requests, and jvm info.


## Contributing

All Scala code is formatted by Scalariform, and passes all checks from Scalastyle and Scapegoat. No Scala file is longer than 100 lines, no line 
longer than 140 characters, and all warnings are treated as errors. Tests are part of the main source tree so they can be run from the browser.

JavaScript is verified by Require.js and UglifyJS. Any Javascript errors or warnings will be treated as compile errors. 

Security filters are in place, and are configured so that resources (css, js, etc) can only be loaded from the configured host.
Inline styles and scripts are prohibited.


## License

Solitaire.gg is free, open-source software under the [MIT license](LICENSE.md).

Copyright (c) 2017 Kyle Unverferth

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
