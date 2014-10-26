(ns Recomendation.core-test
  (:use midje.sweet
        recommendation.neural_network))

;;;;;;;;;; get-vector

(fact 
  (get-vector "1,0,0,0,1,366") =>  ["1" "0" "0" "0" "1" "366"]
  (get-vector "1,3,4,0,1,366") => ["1" "3" "4" "0" "1" "366"])


;;;;;;;;;; sum-categories

(fact (sum-categories "1,3,4,0,1,366") =>  8)

;;;;;;;;;; calculate-categories

(fact  (calculate-categories "1,3,4,0,1,366") => [0.125 0.375 0.5 0.0 1.0 0.244]
       (calculate-categories "5,0,0,0,1,600") => [1.0 0.0 0.0 0.0 1.0 0.4])

