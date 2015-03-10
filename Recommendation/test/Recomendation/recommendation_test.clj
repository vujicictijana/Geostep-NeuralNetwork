(ns Recomendation.recommendation-test
  (:use midje.sweet
        neuralnetwork.recommendation))

;;;;;;;;;; test recommendation

(fact 
  (do 
    (recommendation 41 3 "src/resources/Test.csv")
    (.size data-list))=> 3)

;;;;;;;;;; test prepare-item-data

(fact 
  (prepare-item-data (first data-list))=> "<item id= \"26\" value=\"3.3587763\" />")

;;;;;;;;;; test prepare-xml

(fact
  (prepare-xml data-list) =>
  "<?xml version=\"1.0\" encoding=\"utf-8\"?><items><item id= \"26\" value=\"3.3587763\" /><item id= \"21\" value=\"2.7564921\" /><item id= \"22\" value=\"2.5363715\" /></items>")

;;;;;;;;;; prepare-xml

(fact 
  (prepare-xml data-list) =>
  "<?xml version=\"1.0\" encoding=\"utf-8\"?><items><item id= \"26\" value=\"3.3587763\" /><item id= \"21\" value=\"2.7564921\" /><item id= \"22\" value=\"2.5363715\" /></items>")