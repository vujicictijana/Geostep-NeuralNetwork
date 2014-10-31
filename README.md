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
  <li> Load file <i>recommendation.repl</i> in repl </li> 
  <li> Call start-server function </li> 
  <li> Visit home page at http://localhost:8181/ </li>
  </ul>
  
- From command line: 
   <ul type="1">
  <li> <i>lein repl</i></li> 
  <li> <i>(ns recommendation.repl)</i></li> 
  <li> <i>(require 'recommendation.repl :reload-all)</i></li> 
  <li> <i>(start-server)</i> </li>
  <li> Visit home page at http://localhost:8181/ </li>
  </ul>

<b>Documentation:</b> 

There is link to project API on home page.

<b>Test:</b>

Run midje tests from leiningen commandan line: <i>lein midje</i><br/>
Test files: <br/>
-src/resources/Test.csv (for recommendation) <br/>
-src/resources/ReturnGameTest.xml (for neural network)

<b>Libraries that are used in this project:</b>
- Recommendation - <a href="https://mahout.apache.org/" target="_blank"> Mahout </a>
- Neural network - <a href="http://neuroph.sourceforge.net/" target="_blank"> Neuroph </a>
- Connection to foursquare  - <a href="https://github.com/wallabyfinancial/foursquare-api-java" target="_blank"> Foursquare API</a>
- Documentation - <a href="https://github.com/weavejester/codox" target="_blank">Codox</a>
- Testing - <a href="https://github.com/marick/Midje" target="_blank">Midje </a> ,
<a href="https://github.com/marick/lein-midje" target="_blank">Lein Midje</a>,
<a href="https://github.com/cgrand/enlive" target="_blank">Enlive</a>







