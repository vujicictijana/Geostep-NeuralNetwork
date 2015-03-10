(ns Recomendation.repl-test
  (:require [net.cgrand.enlive-html :as html])
  (:use midje.sweet
        neuralnetwork.repl))

;;; test start-server

(fact 
  (do 
    (start-server)
    (.contains 
      (clojure.string/join 
        (map #(str %) (html/html-resource (java.net.URL. "http://localhost:8181"))))
      "Welcome")) => true)


;;; test recommedation

(fact 
  (.contains
    (clojure.string/join 
      (map #(str %) (html/html-resource (java.net.URL. "http://localhost:8181/home?user=41&number=3&file=src/resources/Test.csv"))))
      ":item") => true)
