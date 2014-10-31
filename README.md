Geostep-NeuralNetwork
=====================
Intelligent system:
- user based recommendation of public games
- neural network that will decide which game is relevant for selling

After download project from git add following jars to src/resources folder:
- json-20090211.jar
- mahout-core-0.9-job.jar
- mahout-core-0.9.jar
- mahout-math-0.9.jar
- neuroph-core-2.9.jar
- slf4j-api-1.7.7.jar
- slf4j-simple-1.7.7.jar

If you are using Eclipse, add same jars to project build path.


<b> Running app from repl:</b>
Namespace recommendation.repl - call start-server function

- From Eclipse:
  1. Import project
  2. Load file recommendation.repl in repl
  3. Call start-server function
  
- From command line: 
  1. lein repl
  2. (ns recommendation.repl)
  3. (require 'recommendation.repl :reload-all)
  4. (start-server)





