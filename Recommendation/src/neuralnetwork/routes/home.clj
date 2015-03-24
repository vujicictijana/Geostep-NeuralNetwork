(ns neuralnetwork.routes.home
  (:require[compojure.core :refer :all]
           [neuralnetwork.views.layout :as layout]
           [neuralnetwork.neural-network :as neural-network]
           [neuralnetwork.recommendation :as recommendation]
           [ring.util.response :as resp])
  (:import [java.io File]))


(defn recommendation 
  [user number file] 
  (do  (recommendation/recommendation (Integer. user) (Integer. number) file )
    (recommendation/prepare-xml neuralnetwork.recommendation/data-list)))

(defn is-game-relevant 
  [game-id] 
  (let [path (str "http://192.168.10.109:81/ReturnGame.aspx?gameNumber=" game-id  "&username=petar")]
    (neural-network/get-relevant game-id path)))

(defn build-xml[txt] 
  (str "<?xml version=\"1.0\" encoding=\"utf-8\"?> <relevant>" txt "</relevant>"))

(defroutes home-routes  
  (GET "/test-xml" [] (resp/resource-response "/resources/ReturnGameTest.xml"))
  (GET "/home" [user number file] (recommendation user number file))
  (GET "/neural-network" [game-id] (build-xml (is-game-relevant game-id))))
  ;;(GET "/documentation" [] (resp/resource-response "../resources/index.html")))




