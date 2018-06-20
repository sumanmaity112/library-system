(ns library.system.controllers.books-controller
  (:use compojure.core)
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [library.system.service.book-service :refer [add-new-book search-book-by-name get-all-books]]))

(defroutes main-routes
           (POST "/add" [^String name no_of_copies] (response (add-new-book name (Integer. no_of_copies))))
           (GET "/books" [] (response (get-all-books)))
           (GET "/search" [^String name] (response (search-book-by-name name)))
           (route/not-found "Page not found"))

(def ^:private wrapped-routes (wrap-routes main-routes wrap-json-response))

(def app
  (->
    (wrap-defaults wrapped-routes (assoc site-defaults :session false :security false))))

