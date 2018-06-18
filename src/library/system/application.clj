(ns library.system.application
  (:use [ring.adapter.jetty :only [run-jetty]]
        [library.system.controllers.books-controller :refer [app]]
        :require [library.system.utils.db-utils :refer [migrate]]))

(defn -main [& args]
  (migrate)
  (run-jetty app {:port 8080})
  (println "Server started on port 8080"))