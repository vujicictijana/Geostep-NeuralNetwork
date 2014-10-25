(ns recommendation.training
   (:require recommendation.game-category)
   (:import [java.io FileWriter]
            [fi.foyt.foursqare.myapi ReadClues]))

;;vector that contains 50 games that will be used to train neural network

(def games-for-training ["142670/1" "550643/1" "291201/1" "971396/1" "789590/1" "733542/1"                         
                         "463454/1" "710354/1" "314029/1" "239673/1" "342813/1" "822701/1" 
                         "591401/1" "414578/1" "706711/0" "789833/0" "312628/0" "603090/0"
                         "146841/0" "762494/0" "354248/1" "580098/1" "110783/1" "943178/0"
                         "359216/1" "617655/0" "338401/0" "682723/1" "423989/1" "847835/1" 
                         "416368/1" "122189/1" "818277/1" "390605/0" "196552/0" "903062/1" 
                         "304465/1" "961613/1" "716192/1" "911491/1" "273180/1" "124555/1" 
                         "204558/1" "448981/1" "311014/1" "903046/0" "876092/0" "737221/0" 
                         "969300/0" "630699/0"])


(defn get-clues-categories 
  "Creates LazySeq that contains data about each game in training vector (game id, relevant/irrelevant and category of each clue"
  [] 
  (map #(conj (recommendation.game-category/get-my-category (first (clojure.string/split % #"/")))
              (second (clojure.string/split % #"/")) 
              (first (clojure.string/split % #"/")))
       games-for-training))



(defn categories-count
  "counts number of clues in each category and returns data in required  format: 
   -number of clues in category \"bussines\",
   -number of clues in category \"social\",
   -number of clues in category \"travel\",
   -number of clues in category \"irrelevant\",
   -game duration,
   -1/0 (relevant/irrelevant)"
  [] 
  (map #(.returnCategoriesCount (new ReadClues) (into-array String %))
       (get-clues-categories)))


(defn prepare-for-file
  "Creates string that will be written in training file"
  [] 
  (apply str (categories-count)))


(defn create-file
  "Creates file with training dataset"
  [name] 
  (let [writer (new FileWriter name)]
    (.append writer (prepare-for-file))
    (.close writer)))


