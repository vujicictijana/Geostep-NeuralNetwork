(ns Recomendation.game-category-test
  (:use midje.sweet
        recommendation.game-category)
   (:import  [fi.foyt.foursqare.myapi ReadClues Foursquare]
            [main Clue]))

;; game-category test


;;;;;;;;;; test check-category

;; 3 travel catgories: 
;; Outdoors & Recreation - 4d4b7105d754a06377d81259 
;; Professional & Other Places - 4d4b7105d754a06375d81259 
;; Arts & Entertainment - 4d4b7104d754a06370d81259 

(fact  
  [(check-category  "4d4b7105d754a06377d81259") 
   (check-category "4d4b7105d754a06375d81259")   
   (check-category "4d4b7104d754a06370d81259")] =>["travel" "travel" "travel"] )

;; 3 social catgories: 
;; Nightlife Spot - 4d4b7105d754a06376d81259 
;; Food - 4d4b7105d754a06374d81259 
;; Event - 4d4b7105d754a06373d81259 

(fact  
  [(check-category  "4d4b7105d754a06376d81259")      
   (check-category "4d4b7105d754a06374d81259") 
   (check-category "4d4b7105d754a06373d81259")] => ["social" "social" "social"])


;; 2 business catgories: 
;; Shop & Service - 4d4b7105d754a06378d81259 
;; Travel & Transport - 4d4b7105d754a06379d81259 

(fact  
  [(check-category  "4d4b7105d754a06378d81259") 
   (check-category "4d4b7105d754a06379d81259")] => ["business" "business"])

;; 3 irrelevant catgories: 
;; Unknown - 000000000000000000000000 - if there is no such place on foursquare
;; Residence - 4e67e38e036454776db1fb3a 
;; College & University - 4d4b7105d754a06372d81259 

(fact  
  [(check-category  "000000000000000000000000") 
     (check-category "4e67e38e036454776db1fb3a") 
     (check-category "4d4b7105d754a06372d81259")] => ["irrelevant" "irrelevant" "irrelevant"])


;;;;;;;;;; test prepare-clue-data

(fact (prepare-clue-data (new Clue "42.1006583570572" "19.0912690758705" "40")) => {"42.1006583570572,19.0912690758705" "40"})

;;;;;;;;; test get-clues 

(fact (println (map #(str %) (get-clues "142670" "http://localhost:8181/test-xml"))) =>
      (println "({\"42.1006583570572,19.0912690758705\" \"40\"}" "{\"42.1070741587757,19.0890374779701\" \"70\"}" "{\"42.1014862389214,19.0935650467873\" \"30\"}" "{\"42.0987159764083,19.0962472558022\" \"70\"}" "{\"42.0937801513544,19.1355469822884\" \"70\"})"))
   
;;;;;;;;; test get-category 

(fact (get-category "42.1006583570572,19.0912690758705" 40) => "4d4b7104d754a06370d81259"
      (get-category "56.000000000000,19.0000000000" 40) => "000000000000000000000000")

;;;;; get-my-category 

(fact (println (map #(str %) (get-my-category "142670" "http://localhost:8181/test-xml"))) =>                
      (println (str "(\"travel\" \"irrelevant\" \"irrelevant\" \"social\" \"travel\")")))
