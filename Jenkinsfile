node {
  stage('init') {
    def sbtHome = tool 'sbt-1.0'
    steps {
      env.sbt = "${sbtHome}/bin/sbt -no-colors -batch"
    }
  }

  stage('pull') {
    steps {
      checkout scm
    }
  }
  stage('build') {
    steps {
      sh "${sbt} dist"
      archiveArtifacts artifacts: '**/target/universal/*.zip', fingerprint: true
    }
  }
  stage('publish') {
    steps {
      echo 'TODO: publish'
    }
  }
}
