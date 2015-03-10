(ns recommendation.routes.home
  (:require[compojure.core :refer :all]
           [recommendation.views.layout :as layout]
           [recommendation.neural-network :as neural-network]
           [recommendation.recommendation :as recommendation]
           [ring.util.response :as resp])
  (:import [java.io File]))


(defn is-game-relevant 
  [game-id] 
  (let [path (str "http://192.168.10.109:81/ReturnGame.aspx?gameNumber=" game-id  "&username=petar")]
    (neural-network/get-relevant game-id path)))


(defn home 
  [user number file] 
  (do  (recommendation/recommendation (Integer. user) (Integer. number) file )
    (recommendation/prepare-xml recommendation.recommendation/data-list)))


(defroutes home-routes  
  (GET "/test-xml" [] (resp/resource-response "/resources/ReturnGameTest.xml"))
  (GET "/home" [user number file] (home user number file))
  (GET "/neural-network" [game-id] (is-game-relevant game-id)))
  ;;(GET "/documentation" [] (resp/resource-response "../resources/index.html")))




