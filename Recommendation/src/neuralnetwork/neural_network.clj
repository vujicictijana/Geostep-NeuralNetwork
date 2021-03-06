(ns neuralnetwork.neural-network
  (:require neuralnetwork.game-category)
   (:import [fi.foyt.foursqare.myapi ReadClues]
            [org.neuroph.core NeuralNetwork ]))


(defn get-clues-categories
  "Returns category for each clue in game with given id,
   using get-my-categories function from neuralnetwork.game-category namespace"
  [game-id path] 
  (conj (neuralnetwork.game-category/get-my-categories game-id path) "" game-id ))


(defn game-data
  "Returns string that contains data about game with given id in required  format: 
   -number of clues in category \"business\",
   -number of clues in category \"social\",
   -number of clues in category \"travel\",
   -number of clues in category \"irrelevant\",
   -public/private
   -game duration"
  [game-id path] 
  (.returnCategoriesCount 
    (new ReadClues game-id path) 
    (into-array String (get-clues-categories game-id path))))


(defn get-vector
  "Returns vector generated from given string, splitted by \",\""
  [text] 
  (clojure.string/split
    (clojure.string/replace (str text) #",\n" "")
    #","))


(defn sum-categories 
  "Returns sum of clues in all categories"
  [text] 
  (reduce + (map #(read-string %) (pop (pop (get-vector text))))))


(defn conj*
     [s x y]
     (conj (vec s) x y))


(defn calculate-input-data
  "Returns vector of normalized data that will be given to neural network"
  [text] 
  (let [sum (sum-categories text)
        vector (get-vector text)]
    (conj*
      (map #(double (/ (read-string %) sum)) (pop (pop vector)))
      (double (read-string (peek (pop vector))))
      (double (/ (read-string (peek  vector)) 1500)))))


(defn get-result
  "Returns neural network prediction for game with given id"
  [game-id path] 
  (let [network (. NeuralNetwork (createFromFile "src/resources/Geostep.nnet"))]
    (do 
      (.setInput 
        network
        (double-array (calculate-input-data(game-data game-id path))))
      (.calculate network)
      (first (.getOutput network)))))

(defn get-result-without-foursquare
     "Returns neural network prediction for specific input - for testing purposes"
     [txt] 
     (let [network (. NeuralNetwork (createFromFile "src/resources/Geostep.nnet"))]
       (do 
         (.setInput 
           network
           (double-array (calculate-input-data txt)))
         (.calculate network)
         (first (.getOutput network)))))

(defn get-relevant
  "Returns \"relevant\" if prediction is greater than 0.75, otherwise it returns \"irrelevant\""
  [game-id path] 
  (if (> (get-result game-id path) 0.75)
    "relevant"
    "irrelevant"))