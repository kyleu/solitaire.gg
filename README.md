# Scalataire

Almost certainly the web's finest card games.

## Running the app

```shell
$ sbt
> run
$ open http://localhost:9000
```

## Projects

* `scalataire` Main web application. Handles online games.
* `sharedJvm` Core game logic and rules definitions, for JVM projects.
* `sharedJs` Shared classes, compiled to Scala.js JavaScript.
* `client` Bare-bones Scala.js app to support offline play.
* `util` Various utility projects, mainly for batch processing.
