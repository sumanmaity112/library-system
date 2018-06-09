(ns library_system.dao.DBConnection
  (:require [clojure.java.jdbc :as jdbc]))


^:private (def pg-db {:dbtype "postgresql"
            :dbname "library"
            :host "localhost"
            :user "admin"
            :password ""})


(def fruit-table-ddl
  (jdbc/create-table-ddl :fruit
                         [[:name "varchar(32)"]
                          [:appearance "varchar(32)"]
                          [:cost :int]
                          [:grade :real]]))



(defn executeQuery [predicate] (jdbc/query pg-db
                                                    predicate))
