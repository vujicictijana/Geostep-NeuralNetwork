(ns recommendation.game-category
  
  (:import  [fi.foyt.foursqare.myapi ReadClues Foursquare]))



;;map for matching geostep categories with foursquare categories

(def my-map
  {:travel "4d4b7105d754a06377d81259,4d4b7105d754a06375d81259,4d4b7104d754a06370d81259"
   :social "4d4b7105d754a06376d81259,4d4b7105d754a06374d81259,4d4b7105d754a06373d81259"
   :business "4d4b7105d754a06378d81259,4d4b7105d754a06379d81259"
   :irrelevant "000000000000000000000000,4e67e38e036454776db1fb3a,4d4b7105d754a06372d81259"})

;;returns corresponding geostep category by foursquare category ID

(defn check-category[category] 
  (clojure.string/join 
    (map #(if (.contains (% my-map) category) 
            (name %) 
            "") 
         (keys my-map))))

;;returns data about clue in required  format: {"lat,lng" "raduis"}

(defn prepare-clue-data[clue]
  {(clojure.string/join  [(.getLat clue) "," (.getLng clue)])
   (.getRadius clue)})

;;returns LazySeq that contains data about all clues in game with given id

(defn get-clues [game-id]  
  (let [read (new ReadClues game-id)
        clues (.returnAllClues read)]
    (map #(prepare-clue-data %) clues)))


;;returns foursqare category for place with given latitude and longitude within given radius

(defn get-category[ll r] 
  (let [foursquare (new Foursquare)
        venue (.getVenue foursquare ll r)]
    (if (= venue nil)  
      "000000000000000000000000"
      (if (= (.getMainCatID foursquare (.getId (first (.getCategories venue)))) nil)
        "000000000000000000000000" 
        (.getMainCatID foursquare (.getId (first (.getCategories venue))))))))


;;returns LazySeq that contains geostep category for each clue in game with given id

(defn get-my-category [game-id] (map #(check-category (get-category (first %)(read-string(second %))))   (map #(first %) (get-clues game-id))))
