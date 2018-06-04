(ns library_system.controllers.UserController
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))




(defn index-page []
  (str "<b> Hello World!!!</b>"))

(defn hello []
  (str "hello"))

(defroutes main-routes
           (GET "/" [] (index-page))
           (GET "/hello" [] (hello))
           (route/resources "/")
           (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)))

