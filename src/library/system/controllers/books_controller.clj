(ns library.system.controllers.books-controller
  (:use compojure.core)
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.route :as route]
            [ring.util.response :refer [response redirect]]
            [ring.middleware.json :refer [wrap-json-response]]
            [library.system.service.book-service :refer [add-new-book search-book-by-name get-all-books update-book-by-id get-all-genres]]
            [library.system.service.borrower-service :refer [borrow-book]]
            [selmer.parser :refer [set-resource-path! cache-off! render-file]]))

(set-resource-path! (clojure.java.io/resource "templates"))
(cache-off!)

(defroutes main-routes
           (GET "/add-new-book" [] (render-file "addNewBook.htm" {:genres (get-all-genres)}))
           (POST "/add" [^String name no_of_copies ^String author-name ^String description ^String logo-url ^String genre] (add-new-book name (Integer. no_of_copies) author-name description logo-url genre) (redirect "/books"))
           (GET "/books" [] (response (get-all-books)))
           (GET "/search" [^String name] (response (search-book-by-name name)))
           (POST "/update" [id ^String name no_of_copies ] (response (update-book-by-id (Integer. id) name (Integer. no_of_copies))))
           (POST "/borrow" [user_id book_id] (response (borrow-book (Integer. user_id) (Integer. book_id) )))
           (route/not-found "Page not found"))

(def ^:private wrapped-routes (wrap-routes main-routes wrap-json-response))

(def app
  (->
    (wrap-defaults wrapped-routes (assoc site-defaults :session false :security false))))