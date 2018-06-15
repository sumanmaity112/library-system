(ns library.system.application
  (:use [ring.adapter.jetty :only [run-jetty]]
        [library.system.controllers.books-controller :refer [app]]))

(defn -main [& args]
  (run-jetty app {:port 8080})
  (println "Server started on port 8080"))