(ns library.system.controllers.books-controller
  (:use compojure.core)
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [library.system.service.book-service :refer [add-new-book search-book-by-name get-all-books update-book-by-id]]
            [library.system.service.borrower-service :refer [borrow-book]]))

(defroutes main-routes
           (POST "/add" [^String name no_of_copies] (response (add-new-book name (Integer. no_of_copies))))
           (GET "/books" [] (response (get-all-books)))
           (GET "/search" [^String name] (response (search-book-by-name name)))
           (POST "/update" [id ^String name no_of_copies ] (response (update-book-by-id (Integer. id) name (Integer. no_of_copies))))
           (POST "/borrow" [user_id book_id] (response (borrow-book (Integer. user_id) (Integer. book_id) )))
           (route/not-found "Page not found"))

(def ^:private wrapped-routes (wrap-routes main-routes wrap-json-response))

(def app
  (->
    (wrap-defaults wrapped-routes (assoc site-defaults :session false :security false))))