(ns recommendation.neural-network
  (:require recommendation.game-category)
   (:import [fi.foyt.foursqare.myapi ReadClues]
            [main GeostepNeuralNetwrok]))

;; returns category for each clue in game with given id
;; using get-my-category function from recommendation.game-category namespace

(defn get-clues-categories[game-id] 
  (conj (recommendation.game-category/get-my-category game-id) "" game-id ))

;;returns string that contains data about game with given id in required  format: 
;;number of clues in category "bussines",
;;number of clues in category "social",
;;number of clues in category "travel",
;;number of clues in category "irrelevant",
;;game duration

(defn categories-count[game-id] 
  (.returnCategoriesCount 
    (new ReadClues) 
    (into-array String (get-clues-categories game-id))))

;;returns vector generated from given string, splitted by ","

(defn get-vector[text] 
  (clojure.string/split
    (clojure.string/replace (str text) #",\n" "")
    #","))

;;returns sum of clues in all categories

(defn sum-categories [text] 
  (reduce + (map #(read-string %) (pop (pop (get-vector text))))))

(defn conj*
     [s x y]
     (conj (vec s) x y))

;; returns vector of normalized data that will be given to neural network

(defn calculate-categories [text] 
  (let [sum (sum-categories text)
        vector (get-vector text)]
    (conj*
      (map #(double (/ (read-string %) sum)) (pop (pop vector)))
      (double (read-string (peek (pop vector))))
      (double (/ (read-string (peek  vector)) 1500)))))

;;returns neural network prediction for game with given id

(defn call-get-result[game-id] 
  (let [network (new GeostepNeuralNetwrok)]
    (.getResult  
      network 
      (into-array Double (calculate-categories (categories-count game-id))))))

;;Returns "relevant" if prediction is greater than 0.75, otherwise it returns "irrelevant"

(defn get-relevant[game-id] 
  (if (> (call-get-result game-id) 0.75)
    "relevant"
    "irrelevant"))