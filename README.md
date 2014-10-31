Geostep-NeuralNetwork
=====================
Intelligent system:
- user based recommendation of public games
- neural network that will decide which game is relevant for selling

After download project from github add following jars to src/resources folder:
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
  <ul type="1">
  <li> Import project </li> 
  <li> Load file recommendation.repl in repl </li> 
  <li> Call start-server function </li> 
  <li> Visit home page at http://localhost:8181/ </li>
  </ul>
  
- From command line: 
   <ul type="1">
  <li> lein repl</li> 
  <li> (ns recommendation.repl)</li> 
  <li> (require 'recommendation.repl :reload-all)</li> 
  <li> (start-server)  </li>
  <li> Visit home page at http://localhost:8181/ </li>
  </ul>





