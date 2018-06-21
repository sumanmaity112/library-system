(ns library.system.dao.books-dao
  (:require [library.system.dbconfig.database-configuration :refer [db-config]]
            [clojure.java.jdbc :as jdbc]
            [clj-time.coerce :refer [to-sql-time]]
            [clj-time.core :refer [now]]))

(defn add-new-book-to-db [^String name, ^Integer no-of-copies]
  (jdbc/insert! db-config :book_info
                {:name name, :no_of_copies no-of-copies}))


(defn get-book-by-name-from-db [^String name]
  (jdbc/query db-config
              ["SELECT name,no_of_copies FROM book_info WHERE name = ?" name]))

(defn get-books []
  (jdbc/query db-config ["select name, no_of_copies from book_info"]))

(defn update-book [id ^String name no_of_copies]
  (jdbc/update! db-config :book_info {:name name :no_of_copies no_of_copies :date_changed (to-sql-time (now))} ["id=?" id]))
