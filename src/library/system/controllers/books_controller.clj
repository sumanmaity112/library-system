(ns library.system.controllers.books-controller
    (:use compojure.core)
    (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
              [compojure.route :as route]
              [ring.util.response :refer [response]]
              [ring.middleware.json :refer [wrap-json-response]]
              [library.system.service.book-service :refer [add-new-book get-book]]))

(defroutes main-routes
  (POST "/add" [name noOfCopies] (response (add-new-book name (Integer. noOfCopies))))
  (GET "/search" [name] (response (get-book name)))
  (route/not-found "Page not found"))

(def ^:private wrapped-routes (wrap-routes main-routes wrap-json-response))

(def app
  (->
    (wrap-defaults wrapped-routes (assoc site-defaults :session false :security false))))

