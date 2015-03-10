(ns neuralnetwork.neural-network
  (:require neuralnetwork.game-category)
   (:import [fi.foyt.foursqare.myapi ReadClues]
            [main GeostepNeuralNetwrok]))

(defn get-clues-categories
  "Returns category for each clue in game with given id,
   using get-my-category function from recommendation.game-category namespace"
  [game-id path] 
  (conj (recommendation.game-category/get-my-category game-id path) "" game-id ))


(defn categories-count
  "Returns string that contains data about game with given id in required  format: 
   -number of clues in category \"bussines\",
   -number of clues in category \"social\",
   -number of clues in category \"travel\",
   -number of clues in category \"irrelevant\",
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


(defn calculate-categories 
  "Returns vector of normalized data that will be given to neural network"
  [text] 
  (let [sum (sum-categories text)
        vector (get-vector text)]
    (conj*
      (map #(double (/ (read-string %) sum)) (pop (pop vector)))
      (double (read-string (peek (pop vector))))
      (double (/ (read-string (peek  vector)) 1500)))))


(defn call-get-result
  "Returns neural network prediction for game with given id"
  [game-id path] 
  (let [network (new GeostepNeuralNetwrok)]
    (.getResult  
      network 
      (into-array Double (calculate-categories (categories-count game-id path))))))


(defn get-relevant
  "Returns \"relevant\" if prediction is greater than 0.75, otherwise it returns \"irrelevant\""
  [game-id path] 
  (if (> (call-get-result game-id path) 0.75)
    "relevant"
    "irrelevant"))