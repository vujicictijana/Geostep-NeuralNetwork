(defproject Recomendation "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [midje "1.6.3"]
                 [enlive "1.1.5"]]
  
  :plugins [[codox "0.8.10"]
            [lein-midje "3.1.1"]]
  
  ;:git-dependencies [["https://github.com/vujicictijana/foursqare-my-api.git"]]
  ;[lein-git-deps "0.0.1-SNAPSHOT"]
  ;:git-dependencies [["https://github.com/vujicictijana/foursqare-my-api.git"]]
  ;:dev-dependencies [[lein-git-deps "0.0.1-SNAPSHOT"]]
  ;:extra-classpath-dirs [".lein-git-deps/foursqare-my-api/foursquare-api/src"]
  
  ;:git-dependencies [["https://github.com/vujicictijana/foursqare-my-api.git"]]
  ;:source-paths [".lein-git-deps/foursqare-my-api/foursquare-api/src"]

  :java-source-paths ["src/fi/foyt/foursqare" "src/main"]
  :resource-paths ["src/resources/json-20090211.jar" "src/resources/neuroph-core-2.9.jar"
                   "src/resources/slf4j-api-1.7.7.jar" "src/resources/slf4j-simple-1.7.7.jar"
                   "src/resources/mahout-core-0.9.jar" "src/resources/mahout-core-0.9-job.jar"
                   "src/resources/mahout-math-0.9.jar"]
  :ring {:handler geostep-clojure.handler/app
         :init geostep-clojure.handler/init
         :destroy geostep-clojure.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]   [lein-idefiles "0.2.0"]]}})
