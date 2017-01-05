node {
   def mvnHome
   stage('Preparation') {
      echo("Check out branch and setup build pipeline environment")
      checkout scm
      mvnHome = tool 'M3'
   }
   
   stage('Compile') {
      echo("Check for compilation issues")
      sh "'${mvnHome}/bin/mvn' clean compile"
   }
   
   stage('Test') {
      echo("Execute Unit tests")
      sh "'${mvnHome}/bin/mvn' test"
      junit '**/target/surefire-reports/TEST-*.xml'
   }
   
   stage('Validate') {
      sh "'${mvnHome}/bin/mvn' -DskipTests install"
      parallel('Code Coverage': {
         sh "'${mvnHome}/bin/mvn' jacoco:check"
      }, 'Style Check': {
         sh "'${mvnHome}/bin/mvn' checkstyle:checkstyle"
      }, 'Find Bugs': {
         sh "'${mvnHome}/bin/mvn' findbugs:check"
      })
   }

   stage('Build') {
      sh "'${mvnHome}/bin/mvn' -DskipTests package"
   }

   stage('Deploy') {
      input "Happy to deploy or what?"
      milestone()
      node {
         echo "Deploying"
      }
   }
}
