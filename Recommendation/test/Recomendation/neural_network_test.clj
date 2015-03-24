(ns Recomendation.neural-network-test
  (:use midje.sweet
        neuralnetwork.neural-network))


;;;;;;;;;; test get-clues-categories

(fact 
  (clojure.string/join (map #(str %) (get-clues-categories  "142670" "http://localhost:8181/test-xml")))
  => "142670traveltravelirrelevantsocialtravel")

;;;;;;;;;; test game-data

(fact 
  (game-data "142670" "http://localhost:8181/test-xml") 
  => "0,1,4,1,1,366,\n")

;;;;;;;;;; test get-vector

(fact 
  (get-vector "1,0,0,0,1,366") =>  ["1" "0" "0" "0" "1" "366"]
  (get-vector "1,3,4,0,1,366") => ["1" "3" "4" "0" "1" "366"])


;;;;;;;;;; test sum-categories

(fact (sum-categories "1,3,4,0,1,366") =>  8)

;;;;;;;;;; test calculate-input-data

(fact  (calculate-input-data "1,3,4,0,1,366") => [0.125 0.375 0.5 0.0 1.0 0.244]
       (calculate-input-data "5,0,0,0,1,600") => [1.0 0.0 0.0 0.0 1.0 0.4])


;;;;;;;;; test get-result

(fact 
  (get-result "142670" "http://localhost:8181/test-xml") => 0.9955363239586293)


;;;;;; test get-relevant 

(fact 
  (get-relevant  "142670" "http://localhost:8181/test-xml") => "relevant")

