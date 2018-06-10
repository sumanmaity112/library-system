(ns library.system.Application
  (:use [ring.adapter.jetty :only [run-jetty]]
            [library.system.controllers.UserController :refer [app]]))

(defn -main [& args]
  (run-jetty app {:port 8080})
  (println "Server started on port 8080"))