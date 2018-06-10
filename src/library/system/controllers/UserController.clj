(ns library.system.controllers.UserController
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [library.system.dao.BooksDao :refer [addNewBook]]))


(defn index-page []
  (str "<b> Hello World!!!</b>"))

(defn hello []
  (str "hello"))

(defn addNewBookToDB [name noOfCopies] (addNewBook name noOfCopies))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/hello" [] (hello))
  (POST "/add" [name noOfCopies] (addNewBookToDB name (Integer. noOfCopies)))
)

(def app
  (-> (handler/site main-routes)))

