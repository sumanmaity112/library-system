
(ns library_system.controllers.UserController
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [library_system.dao.DBConnection :refer [fruit-table-ddl, executeQuery]]))






(defn index-page []
  (str (seq (take 5 (executeQuery ["select * from fruit"])))))

(defn hello []
  (str "hello"))

(defroutes main-routes
           (GET "/" [] (index-page))
           (GET "/hello" [] (hello))
           (route/resources "/")
           (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)))
